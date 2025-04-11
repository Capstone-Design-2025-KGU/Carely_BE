package univ.kgu.carely.domain.team.repository.team;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.entity.TeamMate;

public interface TeamMateRepository extends JpaRepository<TeamMate, Long> {
    Boolean existsByTeamAndMember(Team team, Member member);

    Optional<TeamMate> findByTeamAndMember(Team team, Member member);
}
