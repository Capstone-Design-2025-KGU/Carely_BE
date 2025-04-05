package univ.kgu.carely.domain.meet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.meet.entity.Memory;

public interface MemoryRepository extends JpaRepository<Memory, Long>, CustomMemoryRepository {
}
