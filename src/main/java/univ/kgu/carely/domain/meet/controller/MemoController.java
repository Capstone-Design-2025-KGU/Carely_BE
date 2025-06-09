package univ.kgu.carely.domain.meet.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PutMapping("/{memberId}")
    @Operation(summary = "메모 수정(작성) API", description = "메모를 수정(작성)한다.")
    public ResponseEntity<ResMemoDTO> updateMemo(@PathVariable("memberId") Long memberId,
                                                 @RequestBody ReqMemoUpdateDTO reqMemoUpdateDTO,
                                                 @AuthenticationPrincipal(expression = "member") Member auth) {
        ResMemoDTO resMemoDTO = memoService.updateMemo(memberId, auth, reqMemoUpdateDTO);

        return ResponseEntity.ok(resMemoDTO);
    }

    @PostMapping("/{memberId}")
    @Operation(summary = "메모 생성 API", description = "메모를 생성한다.")
    public ResponseEntity<ResMemoDTO> createMemo(@PathVariable("memberId") Long memberId,
                                                 @RequestBody ReqMemoUpdateDTO reqMemoUpdateDTO,
                                                 @AuthenticationPrincipal(expression = "member") Member auth) {
        ResMemoDTO resultFuture = memoService.createMemo(memberId, reqMemoUpdateDTO, auth);

        return ResponseEntity.ok(resultFuture);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "메모 조회 API", description = "해당 멤버와 연관된 메모를 조회한다.")
    public ResponseEntity<Page<ResMemoDTO>> readMemo(@PathVariable("memberId") Long memberId,
                                                     @PageableDefault(sort = {"id"}, direction = Direction.DESC) Pageable pageable,
                                                     @AuthenticationPrincipal(expression = "member") Member auth) {
        Page<ResMemoDTO> resMemoDTO = memoService.readMemo(memberId, auth, pageable);

        return ResponseEntity.ok(resMemoDTO);
    }

}
