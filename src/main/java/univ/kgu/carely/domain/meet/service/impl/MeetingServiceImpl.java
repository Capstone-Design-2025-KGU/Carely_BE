package univ.kgu.carely.domain.meet.service.impl;

import java.time.LocalDateTime;
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

    public static final String NOT_EXIST_MEETING_EXCEPTION_MESSAGE = "해당 약속이 존재하지 않습니다.";
    public static final String NOT_YOUR_MEETING_EXCEPTION_MESSAGE = "본인이 포함된 약속이 아닙니다.";

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;

    @Override
    @Transactional
    public ResMeetingDTO createMeeting(Long opponentMemberId, ReqMeetingCreateDTO reqMeetingCreateDTO) {
        Member member = memberService.currentMember();

        if (member.getMemberId().equals(opponentMemberId)) {
            throw new RuntimeException("본인에게 약속을 요청할 수 없습니다.");
        }

        if (reqMeetingCreateDTO.getStartTime().isAfter(reqMeetingCreateDTO.getEndTime())) {
            throw new RuntimeException("약속 시작 시간과 끝 시간이 잘못 설정되었습니다.");
        }

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
                new RuntimeException(NOT_EXIST_MEETING_EXCEPTION_MESSAGE));

        if (!member.getMemberId().equals(meeting.getSender().getMemberId())
                && !member.getMemberId().equals(meeting.getReceiver().getMemberId())) {
            throw new RuntimeException(NOT_YOUR_MEETING_EXCEPTION_MESSAGE);
        }

        return toResMeetingDTO(meeting);
    }

    @Override
    @Transactional
    public ResMeetingDTO acceptMeeting(Long meetingId) {
        Member member = memberService.currentMember();
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
                new RuntimeException(NOT_EXIST_MEETING_EXCEPTION_MESSAGE));

        if (!member.getMemberId().equals(meeting.getReceiver().getMemberId())) {
            throw new RuntimeException("본인이 수락할 수 있는 약속이 아닙니다.");
        }

        meeting.setStatus(MeetingStatus.ACCEPT);
        Meeting save = meetingRepository.save(meeting);

        return toResMeetingDTO(save);
    }

    @Override
    @Transactional
    public ResMeetingDTO updateMeeting(Long meetingId, ReqMeetingCreateDTO reqMeetingCreateDTO) {
        Member member = memberService.currentMember();
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
                new RuntimeException(NOT_EXIST_MEETING_EXCEPTION_MESSAGE));

        if (!member.getMemberId().equals(meeting.getSender().getMemberId())) {
            throw new RuntimeException("본인이 수정할 수 있는 약속이 아닙니다.");
        }

        if (meeting.getStatus().equals(MeetingStatus.FINISH)) {
            throw new RuntimeException("끝난 약속은 수정할 수 없습니다.");
        }

        meeting.setStatus(MeetingStatus.PENDING);
        meeting.setStartTime(reqMeetingCreateDTO.getStartTime());
        meeting.setEndTime(reqMeetingCreateDTO.getEndTime());
        meeting.setChore(reqMeetingCreateDTO.getChore());

        Meeting save = meetingRepository.save(meeting);

        return toResMeetingDTO(save);
    }

    @Override
    @Transactional
    public Boolean deleteMeeting(Long meetingId) {
        Member member = memberService.currentMember();
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
                new RuntimeException(NOT_EXIST_MEETING_EXCEPTION_MESSAGE));

        if (!member.getMemberId().equals(meeting.getSender().getMemberId())
                && !member.getMemberId().equals(meeting.getReceiver().getMemberId())){
            throw new RuntimeException(NOT_YOUR_MEETING_EXCEPTION_MESSAGE);
        }

        if(meeting.getStatus().equals(MeetingStatus.FINISH)){
            throw new RuntimeException("완료된 약속은 삭제할 수 없습니다.");
        }

        meetingRepository.delete(meeting);

        return true;
    }

    @Override
    @Transactional
    public ResMeetingDTO finishMeeting(Long meetingId) {
        Member member = memberService.currentMember();
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(()->
                new RuntimeException(NOT_EXIST_MEETING_EXCEPTION_MESSAGE));

        if(!member.getMemberId().equals(meeting.getReceiver().getMemberId())) {
            throw new RuntimeException("약속을 요청받은 사람만 약속을 마무리할 수 있습니다.");
        }

        if(LocalDateTime.now().isBefore(meeting.getEndTime())) {
            throw new RuntimeException("아직 약속 종료시간이 아닙니다.");
        }

        meeting.setStatus(MeetingStatus.FINISH);
        Meeting save = meetingRepository.save(meeting);

        return toResMeetingDTO(save);
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
