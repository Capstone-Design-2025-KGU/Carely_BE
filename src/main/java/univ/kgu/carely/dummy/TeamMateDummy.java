package univ.kgu.carely.dummy;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.entity.TeamMate;
import univ.kgu.carely.domain.team.entity.TeamRole;
import univ.kgu.carely.domain.team.repository.team.TeamMateRepository;
import univ.kgu.carely.domain.team.repository.team.TeamRepository;

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class TeamMateDummy {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final TeamMateRepository teamMateRepository;

    public void makeTeamMate() {
        List<Member> members = memberRepository.findAll();

        List<Team> teams = teamRepository.findAll();

        TeamMate teamMate1 = TeamMate.builder()
                .role(TeamRole.LEADER)
                .team(teams.get(0))
                .member(members.get(0))
                .build();

        TeamMate teamMate2 = TeamMate.builder()
                .role(TeamRole.MATE)
                .team(teams.get(0))
                .member(members.get(1))
                .build();

        TeamMate teamMate3 = TeamMate.builder()
                .role(TeamRole.MATE)
                .team(teams.get(0))
                .member(members.get(2))
                .build();

        TeamMate teamMate4 = TeamMate.builder()
                .role(TeamRole.MATE)
                .team(teams.get(0))
                .member(members.get(3))
                .build();

        TeamMate teamMate5 = TeamMate.builder()
                .role(TeamRole.LEADER)
                .team(teams.get(1))
                .member(members.get(4))
                .build();

        TeamMate teamMate6 = TeamMate.builder()
                .role(TeamRole.MATE)
                .team(teams.get(1))
                .member(members.get(5))
                .build();

        TeamMate teamMate7 = TeamMate.builder()
                .role(TeamRole.MATE)
                .team(teams.get(1))
                .member(members.get(6))
                .build();

        teamMateRepository.saveAll(List.of(teamMate1, teamMate2, teamMate3, teamMate4, teamMate5, teamMate6, teamMate7));
    }

}
