package univ.kgu.carely.domain.meet.service;

import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.meet.dto.request.ReqMeetingCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingDTO;
import univ.kgu.carely.domain.meet.entity.Meeting;

public interface MeetingService {

    /**
     * 약속을 요청(생성)한다.
     *
     * @param opponentMemberId    상대방 ID
     * @param reqMeetingCreateDTO 약속 상세 내용
     * @return 생성된 약속 정보
     */
    ResMeetingDTO createMeeting(Long opponentMemberId, ReqMeetingCreateDTO reqMeetingCreateDTO);

    /**
     * 약속을 조회한다. 
     * 
     * @param meetingId 조회할 약속 ID
     * @return 조회된 약속
     */
    ResMeetingDTO readMeeting(Long meetingId);

    /**
     * 약속을 수락한다.
     *
     * @param meetingId 수락할 약속 ID
     * @return 상태가 수락으로 변경된 해당 약속
     */
    ResMeetingDTO acceptMeeting(Long meetingId);

    /**
     * 약속을 수정한다.
     *
     * @param meetingId           수정할 약속 ID
     * @param reqMeetingCreateDTO 수정할 약속 내용
     * @return 수정된 약속
     */
    ResMeetingDTO updateMeeting(Long meetingId, ReqMeetingCreateDTO reqMeetingCreateDTO);

    /**
     * 약속을 삭제한다.
     * 
     * @param meetingId 삭제할 약속 ID
     * @return 약속 삭제 성공 여부
     */
    Boolean deleteMeeting(Long meetingId);

    ResMeetingDTO toResMeetingDTO(Meeting meeting);
}
