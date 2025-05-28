package univ.kgu.carely.dummy;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.team.entity.Comment;
import univ.kgu.carely.domain.team.entity.Post;
import univ.kgu.carely.domain.team.repository.comment.CommentRepository;
import univ.kgu.carely.domain.team.repository.post.PostRepository;

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class CommentDummy {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void makeComment() {
        Member referMem1 = memberRepository.getReferenceById(1L);
        Member referMem2 = memberRepository.getReferenceById(2L);
        Member referMem3 = memberRepository.getReferenceById(3L);

        Post post = postRepository.getReferenceById(1L);
        Post post1 = postRepository.getReferenceById(2L);

        Comment comment = Comment.builder()
                .content("안뇽하세용")
                .post(post)
                .member(referMem2)
                .build();

        Comment comment1 = Comment.builder()
                .content("반갑습니다!")
                .post(post)
                .member(referMem3)
                .build();

        Comment comment2 = Comment.builder()
                .content("ㅎㅎㅎㅎ")
                .post(post1)
                .member(referMem1)
                .build();

        commentRepository.saveAll(List.of(comment, comment1, comment2));
    }

}
