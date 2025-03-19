package univ.kgu.carely.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.map.dto.request.ReqViewPortInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/search-neighbor")
    @Operation(summary = "이웃 검색 API", description = "이웃을 검색한다.")
    public ResponseEntity<List<ResMemberPublicInfoDTO>> searchNeighbor(@RequestParam(value = "id", defaultValue = "1") Long memberId,
                                                                       @RequestParam(value = "type", defaultValue = "VOLUNTEER") MemberType memberType,
                                                                       @ModelAttribute
                                                                       ReqViewPortInfoDTO viewPortInfoDTO) {
        return ResponseEntity.ok(memberService.searchNeighborMember(memberId, viewPortInfoDTO, memberType));
    }

    @PostMapping("/new")
    public ResponseEntity<ResMemberPrivateInfoDTO> createMember() {
        return null;
    }
}
