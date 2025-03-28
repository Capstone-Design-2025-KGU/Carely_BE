package univ.kgu.carely.domain.team.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import univ.kgu.carely.domain.team.dto.response.ResPostOutlineDTO;
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
        Page<ResPostOutlineDTO> outline = postRepository.findPostOutlineBy("", team, pageRequest);

        assertThat(outline.getContent().get(0).getTitle()).isEqualTo("안녕하세욥");
    }
}