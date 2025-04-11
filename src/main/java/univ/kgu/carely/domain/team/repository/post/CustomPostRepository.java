package univ.kgu.carely.domain.team.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.team.dto.response.ResPostOutlineDTO;
import univ.kgu.carely.domain.team.entity.Team;

public interface CustomPostRepository {
    Page<ResPostOutlineDTO> findPostOutlineBy(String query, Team team, Pageable pageable);
}
