package univ.kgu.carely.domain.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.entity.TeamMate;

public interface TeamMateRepository extends JpaRepository<TeamMate, Long> {
    Boolean existsByTeamAndMember(Team team, Member member);
}
