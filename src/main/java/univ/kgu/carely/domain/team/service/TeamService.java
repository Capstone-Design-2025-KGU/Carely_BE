package univ.kgu.carely.domain.team.service;

import univ.kgu.carely.domain.team.dto.request.ReqCreateTeamDTO;

public interface TeamService {

    Boolean createTeam(ReqCreateTeamDTO reqCreateTeamDTO);
}
