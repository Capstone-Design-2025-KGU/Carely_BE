package univ.kgu.carely.domain.meet.repository.memory;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryCardDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryDTO;
import univ.kgu.carely.domain.member.entity.Member;

public interface CustomMemoryRepository {
    Page<ResMemoryDTO> findPagedMemoryByNameContaining(String query, Member me, Pageable pageable);

    List<ResMemoryCardDTO> findMemoryCardByMember(int count, Member target);
}
