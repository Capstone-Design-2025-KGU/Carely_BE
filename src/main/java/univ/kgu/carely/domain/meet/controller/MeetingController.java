package univ.kgu.carely.domain.meet.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.meet.dto.request.ReqMeetingCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingDTO;
import univ.kgu.carely.domain.meet.service.MeetingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping("/new/{opponentMemberId}")
    @Operation(summary = "약속 요청 API", description = "약속을 요청(생성)한다.")
    public ResponseEntity<ResMeetingDTO> createMeeting(@PathVariable("opponentMemberId") Long opponentMemberId,
                                                       @RequestBody ReqMeetingCreateDTO reqMeetingCreateDTO) {
        ResMeetingDTO meeting = meetingService.createMeeting(opponentMemberId, reqMeetingCreateDTO);

        return ResponseEntity.ok(meeting);
    }

    @GetMapping("/{meetingId}")
    @Operation(summary = "약속 조회 API", description = "약속을 조회한다.")
    public ResponseEntity<ResMeetingDTO> readMeeting(@PathVariable("meetingId") Long meetingId) {
        ResMeetingDTO meeting = meetingService.readMeeting(meetingId);

        return ResponseEntity.ok(meeting);
    }

    @PostMapping("/{meetingId}")
    @Operation(summary = "약속 수락 API", description = "약속을 수락한다.")
    public ResponseEntity<ResMeetingDTO> acceptMeeting(@PathVariable("meetingId") Long meetingId) {
        ResMeetingDTO meeting = meetingService.acceptMeeting(meetingId);

        return ResponseEntity.ok(meeting);
    }

    @PutMapping("/{meetingId}")
    @Operation(summary = "약속 수정 API", description = "약속을 수정한다.")
    public ResponseEntity<ResMeetingDTO> updateMeeting(@PathVariable("meetingId") Long meetingId,
                                                       @RequestBody ReqMeetingCreateDTO reqMeetingCreateDTO){
        ResMeetingDTO meeting = meetingService.updateMeeting(meetingId, reqMeetingCreateDTO);

        return ResponseEntity.ok(meeting);
    }

    @DeleteMapping("/{meetingId}")
    @Operation(summary = "", description = "")
    public ResponseEntity<Boolean> deleteMeeting(@PathVariable("meetingId") Long meetingId){
        Boolean success = meetingService.deleteMeeting(meetingId);

        return ResponseEntity.ok(success);
    }

}
