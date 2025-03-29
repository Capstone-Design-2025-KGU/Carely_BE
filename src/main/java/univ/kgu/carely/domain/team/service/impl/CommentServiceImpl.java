package univ.kgu.carely.domain.team.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.service.MemberService;
import univ.kgu.carely.domain.team.dto.request.ReqCreateCommentDTO;
import univ.kgu.carely.domain.team.dto.response.ResCommentDTO;
import univ.kgu.carely.domain.team.entity.Comment;
import univ.kgu.carely.domain.team.entity.Post;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.repository.CommentRepository;
import univ.kgu.carely.domain.team.repository.PostRepository;
import univ.kgu.carely.domain.team.repository.TeamMateRepository;
import univ.kgu.carely.domain.team.service.CommentService;
import univ.kgu.carely.domain.team.service.PostService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final MemberService memberService;
    private final PostService postService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TeamMateRepository teamMateRepository;

    @Override
    @Transactional
    public Boolean createComment(Long postId, ReqCreateCommentDTO reqCreateCommentDTO) {
        Member member = memberService.currentMember();
        Post post = postRepository.findById(postId).orElseThrow();
        Team team = post.getTeam();

        if(!teamMateRepository.existsByTeamAndMember(team, member)){
            throw new RuntimeException("본인 속한 그룹의 게시글에만 댓글을 달 수 있습니다");
        }

        Comment comment = Comment.builder()
                .content(reqCreateCommentDTO.getContent())
                .member(member)
                .post(post)
                .build();

        commentRepository.save(comment);

        return true;
    }

    @Override
    public ResCommentDTO toResCommentDTO(Comment comment) {
        return ResCommentDTO.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .writer(memberService.toResMemberSmallInfoDTO(comment.getMember()))
                .build();
    }
}
