package univ.kgu.carely.domain.meet.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.meet.dto.request.ReqMeetingCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingSmallInfoDTO;
import univ.kgu.carely.domain.meet.service.MeetingService;
import univ.kgu.carely.domain.member.entity.Member;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping("/new/{opponentMemberId}")
    @Operation(summary = "약속 요청 API", description = "약속을 요청(생성)한다.")
    public ResponseEntity<ResMeetingDTO> createMeeting(@PathVariable("opponentMemberId") Long opponentMemberId,
                                                       @RequestBody ReqMeetingCreateDTO reqMeetingCreateDTO,
                                                       @AuthenticationPrincipal(expression = "member") Member member) {
        ResMeetingDTO meeting = meetingService.createMeeting(member, opponentMemberId, reqMeetingCreateDTO);

        return ResponseEntity.ok(meeting);
    }

    @GetMapping("/{meetingId}")
    @Operation(summary = "약속 조회 API", description = "약속을 조회한다.")
    public ResponseEntity<ResMeetingDTO> readMeeting(@PathVariable("meetingId") Long meetingId,
                                                     @AuthenticationPrincipal(expression = "member") Member member) {
        ResMeetingDTO meeting = meetingService.readMeeting(member, meetingId);

        return ResponseEntity.ok(meeting);
    }

    @PostMapping("/{meetingId}")
    @Operation(summary = "약속 수락 API", description = "약속을 수락한다.")
    public ResponseEntity<ResMeetingDTO> acceptMeeting(@PathVariable("meetingId") Long meetingId,
                                                       @AuthenticationPrincipal(expression = "member") Member member) {
        ResMeetingDTO meeting = meetingService.acceptMeeting(member, meetingId);

        return ResponseEntity.ok(meeting);
    }

    @PutMapping("/{meetingId}")
    @Operation(summary = "약속 수정 API", description = "약속을 수정한다.")
    public ResponseEntity<ResMeetingDTO> updateMeeting(@PathVariable("meetingId") Long meetingId,
                                                       @RequestBody ReqMeetingCreateDTO reqMeetingCreateDTO,
                                                       @AuthenticationPrincipal(expression = "member") Member member){
        ResMeetingDTO meeting = meetingService.updateMeeting(member, meetingId, reqMeetingCreateDTO);

        return ResponseEntity.ok(meeting);
    }

    @PatchMapping("/{meetingId}")
    @Operation(summary = "약속 거부 API", description = "약속을 거부한다.(PENDING 상태로 변경한다.)")
    public ResponseEntity<ResMeetingDTO> rejectMeeting(@PathVariable("meetingId") Long meetingId,
                                                       @AuthenticationPrincipal(expression = "member") Member member) {
        ResMeetingDTO meeting = meetingService.rejectMeeting(member, meetingId);

        return ResponseEntity.ok(meeting);
    }

    @DeleteMapping("/{meetingId}")
    @Operation(summary = "약속 삭제 API", description = "약속을 삭제한다.")
    public ResponseEntity<Boolean> deleteMeeting(@PathVariable("meetingId") Long meetingId,
                                                 @AuthenticationPrincipal(expression = "member") Member member){
        Boolean success = meetingService.deleteMeeting(member, meetingId);

        return ResponseEntity.ok(success);
    }

    @PostMapping("/{meetingId}/end")
    @Operation(summary = "약속 종료 API", description = "약속을 종료한다.")
    public ResponseEntity<ResMeetingDTO> finishMeeting(@PathVariable("meetingId") Long meetingId,
                                                       @AuthenticationPrincipal(expression = "member") Member member) {
        ResMeetingDTO meeting = meetingService.finishMeeting(member, meetingId);

        return ResponseEntity.ok(meeting);
    }

    @GetMapping("/nearest")
    @Operation(summary = "가장 임박한 약속 조회 API", description = "가장 임박한 약속을 조회한다.")
    public ResponseEntity<ResMeetingSmallInfoDTO> getNearestMeeting(@AuthenticationPrincipal(expression = "member") Member member) {
        ResMeetingSmallInfoDTO nearestMeeting = meetingService.getNearestMeeting(member);

        return ResponseEntity.ok(nearestMeeting);
    }

}
