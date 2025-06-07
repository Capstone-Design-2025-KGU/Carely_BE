package univ.kgu.carely.domain.meet.service.impl;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.meet.dto.request.ReqMeetingCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingSmallInfoDTO;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.meet.entity.MeetingStatus;
import univ.kgu.carely.domain.meet.entity.Memory;
import univ.kgu.carely.domain.meet.repository.meeting.MeetingRepository;
import univ.kgu.carely.domain.meet.repository.memory.MemoryRepository;
import univ.kgu.carely.domain.meet.service.MeetingService;
import univ.kgu.carely.domain.meet.util.MeetingMapper;
import univ.kgu.carely.domain.meet.util.MemoryMapper;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    public static final String NOT_EXIST_MEETING_EXCEPTION_MESSAGE = "해당 약속이 존재하지 않습니다.";
    public static final String NOT_YOUR_MEETING_EXCEPTION_MESSAGE = "본인이 포함된 약속이 아닙니다.";

    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;
    private final MemoryRepository memoryRepository;

    private final MeetingMapper meetingMapper;
    private final MemoryMapper memoryMapper;

    @Override
    @Transactional
    public ResMeetingDTO createMeeting(Member self, Long opponentMemberId, ReqMeetingCreateDTO reqMeetingCreateDTO) {
        if (self.getMemberId().equals(opponentMemberId)) {
            throw new RuntimeException("본인에게 약속을 요청할 수 없습니다.");
        }

        if (reqMeetingCreateDTO.getStartTime().isAfter(reqMeetingCreateDTO.getEndTime())) {
            throw new RuntimeException("약속 시작 시간과 끝 시간이 잘못 설정되었습니다.");
        }

        if (reqMeetingCreateDTO.getStartTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("시작시간은 현재 시간 이후부터 설정이 가능합니다.");
        }

        Member receiver = memberRepository.findById(opponentMemberId).orElseThrow(() ->
                new RuntimeException("상대방이 존재하지 않습니다."));

        Meeting meeting = meetingMapper.toEntity(reqMeetingCreateDTO, self, receiver);

        Meeting save = meetingRepository.save(meeting);

        return toResMeetingDTO(save);
    }

    @Override
    @Transactional(readOnly = true)
    public ResMeetingDTO readMeeting(Member member, Long meetingId) {
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
    public ResMeetingDTO acceptMeeting(Member member, Long meetingId) {
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
    public ResMeetingDTO updateMeeting(Member member, Long meetingId, ReqMeetingCreateDTO reqMeetingCreateDTO) {
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
                new RuntimeException(NOT_EXIST_MEETING_EXCEPTION_MESSAGE));

        if (!member.getMemberId().equals(meeting.getSender().getMemberId())) {
            throw new RuntimeException("본인이 수정할 수 있는 약속이 아닙니다.");
        }

        if (meeting.getStatus().equals(MeetingStatus.FINISH)) {
            throw new RuntimeException("끝난 약속은 수정할 수 없습니다.");
        }

        meetingMapper.updateEntity(meeting, reqMeetingCreateDTO);

        Meeting save = meetingRepository.save(meeting);

        return toResMeetingDTO(save);
    }

    @Override
    @Transactional
    public ResMeetingDTO rejectMeeting(Member member, Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
                new RuntimeException(NOT_EXIST_MEETING_EXCEPTION_MESSAGE));

        if (!member.getMemberId().equals(meeting.getReceiver().getMemberId()) &&
                member.getMemberId().equals(meeting.getSender().getMemberId())) {
            throw new RuntimeException("본인이 거절할 수 있는 약속이 아닙니다.");
        }

        if (meeting.getStatus().equals(MeetingStatus.FINISH)) {
            throw new RuntimeException("끝난 약속은 거절할 수 없습니다.");
        }

        meeting.setStatus(MeetingStatus.PENDING);
        meetingRepository.save(meeting);

        return toResMeetingDTO(meeting);
    }

    @Override
    @Transactional
    public Boolean deleteMeeting(Member member, Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
                new RuntimeException(NOT_EXIST_MEETING_EXCEPTION_MESSAGE));

        if (!member.getMemberId().equals(meeting.getSender().getMemberId())
                && !member.getMemberId().equals(meeting.getReceiver().getMemberId())) {
            throw new RuntimeException(NOT_YOUR_MEETING_EXCEPTION_MESSAGE);
        }

        if (meeting.getStatus().equals(MeetingStatus.FINISH)) {
            throw new RuntimeException("완료된 약속은 삭제할 수 없습니다.");
        }

        meetingRepository.delete(meeting);

        return true;
    }

    @Override
    @Transactional
    public ResMeetingDTO finishMeeting(Member member, Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
                new RuntimeException(NOT_EXIST_MEETING_EXCEPTION_MESSAGE));

        if (!member.getMemberId().equals(meeting.getReceiver().getMemberId())) {
            throw new RuntimeException("약속을 요청받은 사람만 약속을 마무리할 수 있습니다.");
        }

        if (!meeting.getStatus().equals(MeetingStatus.ACCEPT)) {
            throw new RuntimeException("수락된 약속이 아니면 종료할 수 없습니다.");
        }

        if (LocalDateTime.now().isBefore(meeting.getEndTime())) {
            throw new RuntimeException("아직 약속 종료시간이 아닙니다.");
        }

        meeting.setStatus(MeetingStatus.FINISH);
        Meeting save = meetingRepository.save(meeting);

        Memory memory = memoryMapper.toEntity(meeting);

        memoryRepository.save(memory);

        return toResMeetingDTO(save);
    }

    public ResMeetingDTO toResMeetingDTO(Meeting meeting) {
        ResMeetingDTO resMeeting = meetingMapper.toResMeetingDto(meeting);
        if (!meeting.getStatus().equals(MeetingStatus.PENDING)) {
            resMeeting.setAddress(meeting.getReceiver().getAddress());
        }

        return resMeeting;
    }

    @Override
    @Transactional(readOnly = true)
    public ResMeetingSmallInfoDTO getNearestMeeting(Member self) {
        return meetingRepository.findNearestMeetingBySenderOrReceiver(self);
    }

}
