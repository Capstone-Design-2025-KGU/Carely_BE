package univ.kgu.carely.dummy;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.team.entity.Post;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.repository.post.PostRepository;
import univ.kgu.carely.domain.team.repository.team.TeamRepository;

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class PostDummy {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;

    public void makePost() {
        Member referMem1 = memberRepository.getReferenceById(1L);
        Member referMem2 = memberRepository.getReferenceById(2L);
        Team referTeam1 = teamRepository.getReferenceById(1L);

        Post post = Post.builder()
                .title("안녕!")
                .content("하세욥")
                .member(referMem1)
                .team(referTeam1)
                .build();

        Post post1 = Post.builder()
                .title("히히")
                .content("하하")
                .member(referMem2)
                .team(referTeam1)
                .build();

        postRepository.saveAll(List.of(post, post1));

    }

}
