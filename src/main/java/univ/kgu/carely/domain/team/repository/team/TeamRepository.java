package univ.kgu.carely.domain.team.repository.team;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.team.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>, CustomTeamRepository {
}
