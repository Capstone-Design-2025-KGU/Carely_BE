package univ.kgu.carely.domain.meet.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.meet.service.MemoService;
import univ.kgu.carely.domain.member.entity.Member;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/current-memo")
    @Operation(summary = "최신 메모 조회 API", description = "해당 멤버의 끝난 약속중 가장 최근 메모를 조회한다.")
    public ResponseEntity<ResMemoDTO> readCurrentFinishedMemo(@RequestParam("member_id") Long memberId,
                                                              @AuthenticationPrincipal(expression = "member") Member member) {
        ResMemoDTO resMemoDTO = memoService.readCurrentFinishedMemo(member, memberId);

        return ResponseEntity.ok(resMemoDTO);
    }

    @PutMapping("/{meetingId}")
    @Operation(summary = "메모 수정(작성) API", description = "메모를 수정(작성)한다.")
    public ResponseEntity<ResMemoDTO> updateMemo(@PathVariable("meetingId") Long meetingId,
                                                 @RequestBody ReqMemoUpdateDTO reqMemoUpdateDTO,
                                                 @AuthenticationPrincipal(expression = "member") Member member) {
        ResMemoDTO resMemoDTO = memoService.updateMemo(member, meetingId, reqMemoUpdateDTO);

        return ResponseEntity.ok(resMemoDTO);
    }

    @GetMapping("/{meetingId}")
    @Operation(summary = "메모 조회 API", description = "해당 약속과 연관된 메모를 조회한다.")
    public ResponseEntity<ResMemoDTO> readMemo(@PathVariable("meetingId") Long meetingId,
                                               @AuthenticationPrincipal(expression = "member") Member member) {
        ResMemoDTO resMemoDTO = memoService.readMemo(member, meetingId);

        return ResponseEntity.ok(resMemoDTO);
    }

}
