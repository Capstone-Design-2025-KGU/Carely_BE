package univ.kgu.carely.domain.team.service;

import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.team.dto.request.ReqCreateTeamDTO;

public interface TeamService {

    Boolean createTeam(ReqCreateTeamDTO reqCreateTeamDTO);

    @Transactional
    Boolean joinTeam(Long teamId);
}
