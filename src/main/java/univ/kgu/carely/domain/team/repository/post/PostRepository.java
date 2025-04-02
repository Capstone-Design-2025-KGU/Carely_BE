package univ.kgu.carely.domain.team.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.team.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository {
}
