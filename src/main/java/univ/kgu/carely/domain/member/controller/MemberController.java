package univ.kgu.carely.domain.member.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.map.dto.request.ReqViewPortInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/search-neighbor")
    public ResponseEntity<List<ResMemberPublicInfoDTO>> searchNeighbor(@RequestParam("id") Long memberId,
                                                                       @RequestParam("type") MemberType memberType,
                                                                       @RequestBody
                                                                       ReqViewPortInfoDTO viewPortInfoDTO) {
        return ResponseEntity.ok(memberService.searchNeighborMember(memberId, viewPortInfoDTO, memberType));
    }

}
