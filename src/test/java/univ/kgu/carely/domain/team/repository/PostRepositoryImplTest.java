package univ.kgu.carely.domain.team.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import univ.kgu.carely.domain.team.entity.Team;

@SpringBootTest
@ActiveProfiles("test")
class PostRepositoryImplTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    @DisplayName("그룹 게시글 페이징 조회 쿼리 테스트")
    void findPostOutlineBy() {
        Team team = teamRepository.findById(1L).orElseThrow();
        PageRequest pageRequest = PageRequest.of(0,10);
        postRepository.findPostOutlineBy("", team, pageRequest);
    }
}