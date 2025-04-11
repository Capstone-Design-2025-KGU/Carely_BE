package univ.kgu.carely.domain.team.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.team.dto.request.ReqCreateTeamDTO;
import univ.kgu.carely.domain.team.dto.response.ResTeamOutlineDTO;
import univ.kgu.carely.domain.team.service.TeamService;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/new")
    @Operation(summary = "그룹 생성 API", description = "그룹 생성")
    public ResponseEntity<Boolean> createTeam(@RequestBody ReqCreateTeamDTO reqCreateTeamDTO,
                                              @AuthenticationPrincipal(expression = "member") Member member) {
        Boolean success = teamService.createTeam(member, reqCreateTeamDTO);

        return ResponseEntity.ok(success);
    }

    @PostMapping("/{teamId}/join")
    @Operation(summary = "그룹 가입 API", description = "그룹 가입 API")
    public ResponseEntity<Boolean> joinTeam(@PathVariable("teamId") Long teamId,
                                            @AuthenticationPrincipal(expression = "member") Member member) {
        Boolean success = teamService.joinTeam(member, teamId);

        return ResponseEntity.ok(success);
    }

    @DeleteMapping("/{teamId}/exit")
    @Operation(summary = "그룹 탈퇴 API", description = "그룹 탈퇴 API")
    public ResponseEntity<Boolean> exitTeam(@PathVariable("teamId") Long teamId,
                                            @AuthenticationPrincipal(expression = "member") Member member){
        Boolean success = teamService.exitTeam(member, teamId);

        return ResponseEntity.ok(success);
    }

    @DeleteMapping("/{teamId}/close")
    @Operation(summary = "그룹 폐쇄 API", description = "그룹 폐쇄 API")
    public ResponseEntity<Boolean> closeTeam(@PathVariable("teamId") Long teamId,
                                             @AuthenticationPrincipal(expression = "member") Member member) {
        Boolean success = teamService.closeTeam(member, teamId);

        return ResponseEntity.ok(success);
    }

    @GetMapping("/search-neighbor")
    @Operation(summary = "주변 그룹 검색 API", description = "주변에 존재하는 그룹을 찾는다")
    public ResponseEntity<Page<ResTeamOutlineDTO>> searchNeighbor(Pageable pageable,
                                                                  @AuthenticationPrincipal(expression = "member") Member member) {
        Page<ResTeamOutlineDTO> resTeamOutlineDTOS = teamService.searchNeighbor(member, pageable);

        return ResponseEntity.ok(resTeamOutlineDTOS);
    }
}
