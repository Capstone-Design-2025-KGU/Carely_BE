package univ.kgu.carely.domain.meet.service;

import univ.kgu.carely.domain.meet.dto.request.ReqMeetingCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingDTO;
import univ.kgu.carely.domain.meet.entity.Meeting;

public interface MeetingService {

    /**
     * 미팅(만남)을 요청(생성)한다.
     *
     * @param opponentMemberId    상대방 ID
     * @param reqMeetingCreateDTO 미팅 상세 내용
     * @return 생성된 미팅 정보
     */
    ResMeetingDTO createMeeting(Long opponentMemberId, ReqMeetingCreateDTO reqMeetingCreateDTO);

    ResMeetingDTO readMeeting(Long meetingId);

    ResMeetingDTO toResMeetingDTO(Meeting meeting);
}
