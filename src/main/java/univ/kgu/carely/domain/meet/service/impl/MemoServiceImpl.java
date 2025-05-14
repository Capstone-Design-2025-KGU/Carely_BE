package univ.kgu.carely.domain.meet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.meet.entity.Memo;
import univ.kgu.carely.domain.meet.repository.meeting.MeetingRepository;
import univ.kgu.carely.domain.meet.repository.memo.MemoRepository;
import univ.kgu.carely.domain.meet.service.MemoService;
import univ.kgu.carely.domain.meet.util.MemoMapper;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;
    private final MemberRepository memberRepository;

    private final MemoMapper memoMapper;

    @Override
    @Transactional
    public ResMemoDTO updateMemo(Long memberId, Member member, ReqMemoUpdateDTO reqMemoUpdateDTO) {
        Member opponent = memberRepository.getReferenceById(memberId);
        Memo memo = memoRepository.findCurrentMemoByMember(opponent);

        memoMapper.updateMemo(memo, reqMemoUpdateDTO);
        memo = memoRepository.save(memo);

        return memoMapper.toResMemoDto(memo);
    }

    @Override
    @Transactional(readOnly = true)
    public ResMemoDTO readMemo(Member member, Long memberId) {
        Member opponent = memberRepository.getReferenceById(memberId);
        Memo memo = memoRepository.findCurrentMemoByMember(opponent);

        return memoMapper.toResMemoDto(memo);
    }

}
