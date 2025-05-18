package univ.kgu.carely.domain.meet.service;

import univ.kgu.carely.domain.meet.dto.request.ReqMeetingCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingSmallInfoDTO;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.member.entity.Member;

public interface MeetingService {

    /**
     * 약속을 요청(생성)한다.
     *
     * @param member
     * @param opponentMemberId    상대방 ID
     * @param reqMeetingCreateDTO 약속 상세 내용
     * @return 생성된 약속 정보
     */
    ResMeetingDTO createMeeting(Member member, Long opponentMemberId, ReqMeetingCreateDTO reqMeetingCreateDTO);

    /**
     * 약속을 조회한다.
     *
     * @param member
     * @param meetingId 조회할 약속 ID
     * @return 조회된 약속
     */
    ResMeetingDTO readMeeting(Member member, Long meetingId);

    /**
     * 약속을 수락한다.
     *
     * @param member
     * @param meetingId 수락할 약속 ID
     * @return 상태가 수락으로 변경된 해당 약속
     */
    ResMeetingDTO acceptMeeting(Member member, Long meetingId);

    /**
     * 약속을 수정한다.
     *
     * @param member
     * @param meetingId           수정할 약속 ID
     * @param reqMeetingCreateDTO 수정할 약속 내용
     * @return 수정된 약속
     */
    ResMeetingDTO updateMeeting(Member member, Long meetingId, ReqMeetingCreateDTO reqMeetingCreateDTO);

    /**
     * 약속을 거절한다.
     *
     * @param member
     * @param meetingId 거절하려고 하는 약속
     * @return 거절된 상태가 반영된 약속
     */
    ResMeetingDTO rejectMeeting(Member member, Long meetingId);

    /**
     * 약속을 삭제한다.
     *
     * @param member
     * @param meetingId 삭제할 약속 ID
     * @return 약속 삭제 성공 여부
     */
    Boolean deleteMeeting(Member member, Long meetingId);

    /**
     * 약속을 마무리한다.
     *
     * @param member
     * @param meetingId 마무리 하려고 하는 약속 ID
     * @return 마무리 처리 된 약속
     */
    ResMeetingDTO finishMeeting(Member member, Long meetingId);

    ResMeetingSmallInfoDTO getNearestMeeting(Member self);
}
