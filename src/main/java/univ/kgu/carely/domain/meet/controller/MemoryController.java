package univ.kgu.carely.domain.meet.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoryUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryCardDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryDTO;
import univ.kgu.carely.domain.meet.service.MemoryService;
import univ.kgu.carely.domain.member.entity.Member;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memories")
public class MemoryController {

    private final MemoryService memoryService;

    @GetMapping("")
    @Operation(summary = "함께한 추억 검색 API", description = "현재 로그인한 사용자의 함께한 추억(방명록) 을 검색한다.")
    public ResponseEntity<Page<ResMemoryDTO>> readPagedMemory(@RequestParam(value = "query", defaultValue = "") String query,
                                                              @PageableDefault() Pageable pageable,
                                                              @AuthenticationPrincipal(expression = "member") Member member) {
        Page<ResMemoryDTO> resMemoryDTOS = memoryService.readPagedMemory(member, query, pageable);

        return ResponseEntity.ok(resMemoryDTOS);
    }

    @PostMapping("/{memoryId}")
    @Operation(summary = "함께한 추억 작성(수정) API", description = "함께한 추억(방명록) 을 작성한다.")
    public ResponseEntity<ResMemoryDTO> updateMemory(@PathVariable("memoryId") Long memoryId,
                                                     @RequestBody ReqMemoryUpdateDTO reqMemoryUpdateDTO,
                                                     @AuthenticationPrincipal(expression = "member") Member member) {
        ResMemoryDTO resMemoryDTO = memoryService.updateMemory(member, memoryId, reqMemoryUpdateDTO);

        return ResponseEntity.ok(resMemoryDTO);
    }

    @GetMapping("/{memoryId}")
    @Operation(summary = "함께한 추억 조회 API", description = "함께한 추억(방명록) 을 조회한다.")
    public ResponseEntity<ResMemoryDTO> readMemory(@PathVariable("memoryId") Long memoryId,
                                                   @AuthenticationPrincipal(expression = "member") Member member) {
        ResMemoryDTO resMemoryDTO = memoryService.readMemory(member, memoryId);

        return ResponseEntity.ok(resMemoryDTO);
    }

    @GetMapping("/others")
    @Operation(summary = "다른 사용자의 함께한 추억 목록 조회 API", description = "Memory 를 Card 형태의 컴포넌트에 표현하기 위한 조회 API")
    public ResponseEntity<List<ResMemoryCardDTO>> getOthersMemories(@RequestParam("mid") Long memberId,
                                                                    @AuthenticationPrincipal(expression = "member") Member auth){
        List<ResMemoryCardDTO> resMemoryCardDTOs = memoryService.getOthersMemories(memberId, auth);

        return ResponseEntity.ok(resMemoryCardDTOs);
    }

}
