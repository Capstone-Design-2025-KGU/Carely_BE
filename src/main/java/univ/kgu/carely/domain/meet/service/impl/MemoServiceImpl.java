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

    private final MeetingRepository meetingRepository;
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
    public ResMemoDTO readCurrentFinishedMemo(Member member, Long memberId) {
        Member memoMember = memberRepository.getReferenceById(memberId);

        if (!meetingRepository.existsBySenderAndReceiverAndMeetingStatusIsAccept(member, memoMember)) {
            throw new RuntimeException("해당 유저와 수락된 약속이 존재해야지만 메모를 열람을 수 있습니다.");
        }

        Memo memo = memoRepository.findCurrentMemoByMember(memoMember);

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
