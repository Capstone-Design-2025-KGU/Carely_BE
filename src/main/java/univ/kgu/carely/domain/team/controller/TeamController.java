package univ.kgu.carely.domain.team.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.team.dto.request.ReqCreateTeamDTO;
import univ.kgu.carely.domain.team.service.TeamService;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/new")
    @Operation(summary = "팀 생성 API", description = "팀 생성")
    public ResponseEntity<Boolean> createTeam(@RequestBody ReqCreateTeamDTO reqCreateTeamDTO) {
        Boolean success = teamService.createTeam(reqCreateTeamDTO);

        return ResponseEntity.ok(success);
    }

}
