package univ.kgu.carely.domain.meet.repository.memo;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.meet.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long>, CustomMemoRepository {
}
