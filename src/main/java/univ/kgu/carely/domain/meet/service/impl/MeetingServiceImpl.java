package univ.kgu.carely.domain.meet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.meet.dto.request.ReqMeetingCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingDTO;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.meet.entity.MeetingStatus;
import univ.kgu.carely.domain.meet.repository.MeetingRepository;
import univ.kgu.carely.domain.meet.service.MeetingService;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;

    @Override
    @Transactional
    public ResMeetingDTO createMeeting(Long opponentMemberId, ReqMeetingCreateDTO reqMeetingCreateDTO) {
        Member member = memberService.currentMember();
        Member opponentMember = memberRepository.findById(opponentMemberId).orElseThrow(() ->
                new RuntimeException("상대방이 존재하지 않습니다."));

        Meeting meeting = Meeting.builder()
                .startTime(reqMeetingCreateDTO.getStartTime())
                .endTime(reqMeetingCreateDTO.getEndTime())
                .chore(reqMeetingCreateDTO.getChore())
                .status(MeetingStatus.PENDING)
                .sender(member)
                .receiver(opponentMember)
                .build();

        Meeting save = meetingRepository.save(meeting);

        return toResMeetingDTO(save);
    }

    @Override
    @Transactional(readOnly = true)
    public ResMeetingDTO readMeeting(Long meetingId) {
        Member member = memberService.currentMember();
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
                new RuntimeException("해당 만남이 존재하지 않습니다."));

        if (!member.getMemberId().equals(meeting.getSender().getMemberId())
                && !member.getMemberId().equals(meeting.getReceiver().getMemberId())) {
            throw new RuntimeException("본인이 포함된 만남이 아닙니다.");
        }

        return toResMeetingDTO(meeting);
    }

    @Override
    public ResMeetingDTO toResMeetingDTO(Meeting meeting) {
        return ResMeetingDTO.builder()
                .meetingId(meeting.getId())
                .startTime(meeting.getStartTime())
                .endTime(meeting.getEndTime())
                .chore(meeting.getChore())
                .status(meeting.getStatus())
                .createdAt(meeting.getCreatedAt())
                .updatedAt(meeting.getUpdatedAt())
                .sender(memberService.toResMemberSmallInfoDTO(meeting.getSender()))
                .receiver(memberService.toResMemberSmallInfoDTO(meeting.getReceiver()))
                .build();
    }

}
