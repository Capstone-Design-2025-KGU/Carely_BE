package univ.kgu.carely.domain.meet.repository.memo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.meet.entity.Memo;
import univ.kgu.carely.domain.member.entity.Member;

public interface MemoRepository extends JpaRepository<Memo, Long>, CustomMemoRepository {
    Page<Memo> findMemoByMemberOrderByIdDesc(Member member, Pageable pageable);
}
