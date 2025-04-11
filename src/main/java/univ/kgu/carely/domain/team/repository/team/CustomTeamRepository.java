package univ.kgu.carely.domain.team.repository.team;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.team.dto.response.ResTeamOutlineDTO;

public interface CustomTeamRepository {
    Page<ResTeamOutlineDTO> findTeamOutlineWithinDistance(Member member, int meter, Pageable pageable);
}
