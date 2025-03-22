package univ.kgu.carely.domain.team.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.service.MemberService;
import univ.kgu.carely.domain.team.dto.request.ReqCreateTeamDTO;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.entity.TeamMate;
import univ.kgu.carely.domain.team.entity.TeamRole;
import univ.kgu.carely.domain.team.repository.TeamRepository;
import univ.kgu.carely.domain.team.service.TeamService;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final MemberService memberService;
    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public Boolean createTeam(ReqCreateTeamDTO reqCreateTeamDTO) {
        Member member = memberService.currentMember();

        Team team = Team.builder()
                .teamName(reqCreateTeamDTO.getTeamName())
                .address(reqCreateTeamDTO.getAddress())
                .build();

        teamRepository.save(team);

        TeamMate teamMate = TeamMate.builder()
                .memberId(member)
                .teamId(team)
                .role(TeamRole.LEADER)
                .build();

        team.getTeamMates().add(teamMate);

        return true;
    }

}
