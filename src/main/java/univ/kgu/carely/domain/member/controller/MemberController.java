package univ.kgu.carely.domain.member.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.common.embeded.skill.Skill;
import univ.kgu.carely.domain.map.dto.request.ReqCoordinationDTO;
import univ.kgu.carely.domain.member.dto.request.ReqMemberCreateDTO;
import univ.kgu.carely.domain.member.dto.request.ReqUpdateSkillDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberMapDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMembersRecommendedDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/search-neighbor")
    @Operation(summary = "이웃 검색 API", description = "이웃을 검색한다.")
    public ResponseEntity<List<ResMemberMapDTO>> searchNeighbor(@RequestParam(value = "query", defaultValue = "") String query,
                                                                @AuthenticationPrincipal(expression = "member") Member member) {
        List<ResMemberMapDTO> memberList = memberService.searchNeighborMember(member, query);

        return ResponseEntity.ok(memberList);
    }

    @PostMapping("/new")
    @Operation(summary = "회원가입 API", description = "회원가입")
    public ResponseEntity<ResMemberPrivateInfoDTO> createMember(@RequestBody ReqMemberCreateDTO reqMemberCreateDTO) {
        ResMemberPrivateInfoDTO member = memberService.createMember(reqMemberCreateDTO);

        return ResponseEntity.ok(member);
    }

    @GetMapping("/check/username")
    @Operation(summary = "중복 username 검사 API", description = "중복된 username을 검사한다.")
    public ResponseEntity<Boolean> isDuplicatedUsername(String username) {
        Boolean duplicatedUsername = memberService.isDuplicatedUsername(username);

        return ResponseEntity.ok(duplicatedUsername);
    }

    @GetMapping("/check/phone-number")
    @Operation(summary = "중복 전화번호 검사 API", description = "중복된 전화번호 검사를 진행한다.")
    public ResponseEntity<Boolean> isDuplicatedPhoneNumber(String phoneNumber) {
        Boolean duplicatedPhoneNumber = memberService.isDuplicatedPhoneNumber(phoneNumber);

        return ResponseEntity.ok(duplicatedPhoneNumber);
    }

    @PostMapping("/verify")
    @Operation(summary = "동네 인증 API", description = "GPS 좌표를 기준으로 설정한 주소와 거리차이를 계산해 동네 인증을 진행한다.")
    public ResponseEntity<Boolean> verifyNeighbor(@RequestBody ReqCoordinationDTO reqCoordinationDTO,
                                                  @AuthenticationPrincipal(expression = "member") Member member) {
        Boolean verified = memberService.verifyNeighbor(member, reqCoordinationDTO);

        return ResponseEntity.ok(verified);
    }

    @GetMapping("/profile/my")
    @Operation(summary = "개인 정보 조회 API", description = "개인 정보 조회")
    public ResponseEntity<ResMemberPrivateInfoDTO> getPrivateInfo(@AuthenticationPrincipal(expression = "member") Member member){
        ResMemberPrivateInfoDTO privateInfo = memberService.getPrivateInfo(member);

        return ResponseEntity.ok(privateInfo);
    }

    @PutMapping("/profile/my/skill")
    @Operation(summary = "능력 수정 API", description = "능력 수정을 진행한다.")
    public ResponseEntity<Skill> updateSkill(@RequestBody ReqUpdateSkillDTO reqUpdateSkillDTO,
                                             @AuthenticationPrincipal(expression = "member") Member member){
        Skill skill = memberService.updateSkill(member, reqUpdateSkillDTO);

        return ResponseEntity.ok(skill);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "특정 멤버의 정보 API", description = "특정 멤버의 정보를 조회한다.")
    public ResponseEntity<ResMemberPublicInfoDTO> findMember(@PathVariable("memberId") Long memberId,
                                                             @AuthenticationPrincipal(expression = "member") Member member) {
        ResMemberPublicInfoDTO publicInfo = memberService.getMemberPublicInfo(memberId, member);

        return ResponseEntity.ok(publicInfo);
    }

    @GetMapping("/recommend")
    @Operation(summary = "추천 이웃 정보 API", description = "이웃중 나와 잘 맞는 사람을 추천받는다.")
    public ResponseEntity<Page<ResMembersRecommendedDTO>> getRecommendedNeighbors(@PageableDefault Pageable pageable,
                                                                                  @AuthenticationPrincipal(expression = "member") Member member) {
        Page<ResMembersRecommendedDTO> recommendedNeighbors = memberService.getRecommendedNeighbors(pageable, member);

        return ResponseEntity.ok(recommendedNeighbors);
    }

}
