package univ.kgu.carely.domain.team.repository.team;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.team.dto.response.ResTeamOutlineDTO;

public interface CustomTeamRepository {
    Page<ResTeamOutlineDTO> findTeamOutlineWithinDistance(Point point, int meter, Pageable pageable);
}
