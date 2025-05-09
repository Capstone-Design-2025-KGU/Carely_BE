package univ.kgu.carely.domain.meet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.meet.entity.Memo;
import univ.kgu.carely.domain.meet.repository.meeting.MeetingRepository;
import univ.kgu.carely.domain.meet.repository.memo.MemoRepository;
import univ.kgu.carely.domain.meet.service.MemoService;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.member.service.MemberService;
import univ.kgu.carely.domain.member.util.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MeetingRepository meetingRepository;
    private final MemoRepository memoRepository;
    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public ResMemoDTO updateMemo(Member member, Long meetingId, ReqMemoUpdateDTO reqMemoUpdateDTO) {
        Meeting meeting = meetingRepository.getReferenceById(meetingId);
        Memo memo = memoRepository.findByMeeting(meeting);

        if(!member.getMemberId().equals(memo.getWriter().getMemberId())){
            throw new RuntimeException("본인이 수정 가능한 메모가 아닙니다.");
        }

        memo.setCommSum(reqMemoUpdateDTO.getComm());
        memo.setMealSum(reqMemoUpdateDTO.getMeal());
        memo.setToiletSum(reqMemoUpdateDTO.getToilet());
        memo.setBathSum(reqMemoUpdateDTO.getBath());
        memo.setWalkSum(reqMemoUpdateDTO.getWalk());

        Memo save = memoRepository.save(memo);

        return toResMemoDTO(save);
    }

    @Override
    @Transactional(readOnly = true)
    public ResMemoDTO readCurrentFinishedMemo(Member member, Long memberId) {
        Member memoMember = memberRepository.getReferenceById(memberId);

        if(!meetingRepository.existsBySenderAndReceiverAndMeetingStatusIsAccept(member, memoMember)){
            throw new RuntimeException("해당 유저와 수락된 약속이 존재해야지만 메모를 열람을 수 있습니다.");
        }

        Memo memo = memoRepository.findCurrentMemoByMemberAndMeetingStatusFinish(memoMember);

        return toResMemoDTO(memo);
    }

    @Override
    @Transactional(readOnly = true)
    public ResMemoDTO readMemo(Member member, Long meetingId) {
        Meeting meeting = meetingRepository.getReferenceById(meetingId);
        Memo memo = memoRepository.findByMeeting(meeting);

        if(!member.getMemberId().equals(meeting.getSender().getMemberId())){
            throw new RuntimeException("본인이 작성한 메모가 아닙니다.");
        }

        return toResMemoDTO(memo);
    }

    @Override
    public ResMemoDTO toResMemoDTO(Memo memo) {
        return ResMemoDTO.builder()
                .memoId(memo.getId())
                .commSum(memo.getCommSum())
                .mealSum(memo.getMealSum())
                .toiletSum(memo.getToiletSum())
                .bathSum(memo.getBathSum())
                .walkSum(memo.getWalkSum())
                .createdAt(memo.getCreatedAt())
                .member(memberMapper.toResMemberSmallInfoDto(memo.getMember()))
                .writer(memberMapper.toResMemberSmallInfoDto(memo.getWriter()))
                .build();
    }

}
