package univ.kgu.carely.domain.team.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.team.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
