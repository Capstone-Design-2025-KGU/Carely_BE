package univ.kgu.carely.domain.meet.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoSumCreateDTO;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoSumDTO;
import univ.kgu.carely.domain.meet.entity.Memo;
import univ.kgu.carely.domain.meet.repository.memo.MemoRepository;
import univ.kgu.carely.domain.meet.service.MemoService;
import univ.kgu.carely.domain.meet.service.MemoSumService;
import univ.kgu.carely.domain.meet.util.MemoMapper;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemoSumService memoSumService;

    private final MemoRepository memoRepository;
    private final MemberRepository memberRepository;

    private final MemoMapper memoMapper;

    @Override
    @Transactional
    public ResMemoDTO updateMemo(Long memberId, Member auth, ReqMemoUpdateDTO reqMemoUpdateDTO) {
        Member opponent = memberRepository.getReferenceById(memberId);
        Memo memo = memoRepository.findMemoByMember(opponent);

        if (memo == null) {
            Memo entity = new Memo();
            entity.setMember(auth);

            memoRepository.save(entity);
        }

        ReqMemoSumCreateDTO reqMemoSumCreateDTO = getReqMemoSumCreateDTO(memo, reqMemoUpdateDTO);
        Mono<ResMemoSumDTO> resMemoSumDTOMono = memoSumService.summarize(reqMemoSumCreateDTO);
        // ToDo : 비동기적으로 저장 가능한 방법을 찾을 경우 적용 필요.
        ResMemoSumDTO block = resMemoSumDTOMono.block();
        memoMapper.updateMemo(memo, block);

        memo = memoRepository.save(memo);

        return memoMapper.toResMemoDto(memo);
    }

    ReqMemoSumCreateDTO getReqMemoSumCreateDTO(Memo prevMemo, ReqMemoUpdateDTO reqMemoUpdateDTO) {
        ReqMemoSumCreateDTO dto = new ReqMemoSumCreateDTO();
        String sb = prevMemo.getComm()
                + prevMemo.getMeal()
                + prevMemo.getMedic()
                + prevMemo.getHealth()
                + prevMemo.getWalk()
                + prevMemo.getToilet()
                + reqMemoUpdateDTO.getOriginal();

        dto.setNotes(sb);
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public ResMemoDTO readMemo(Long memberId, Member member) {
        Member opponent = memberRepository.getReferenceById(memberId);
        Memo memo = memoRepository.findMemoByMember(opponent);

        return memoMapper.toResMemoDto(memo);
    }

}
