package univ.kgu.carely.domain.team.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.service.MemberService;
import univ.kgu.carely.domain.member.util.MemberMapper;
import univ.kgu.carely.domain.team.dto.request.ReqCommentDTO;
import univ.kgu.carely.domain.team.dto.response.ResCommentDTO;
import univ.kgu.carely.domain.team.entity.Comment;
import univ.kgu.carely.domain.team.entity.Post;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.repository.comment.CommentRepository;
import univ.kgu.carely.domain.team.repository.post.PostRepository;
import univ.kgu.carely.domain.team.repository.team.TeamMateRepository;
import univ.kgu.carely.domain.team.service.CommentService;
import univ.kgu.carely.domain.team.util.CommentMapper;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TeamMateRepository teamMateRepository;

    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public Boolean createComment(Member member, Long postId, ReqCommentDTO reqCommentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new RuntimeException("찾으려는 게시글이 없습니다."));
        Team team = post.getTeam();

        if (!teamMateRepository.existsByTeamAndMember(team, member)) {
            throw new RuntimeException("본인 속한 그룹의 게시글에만 댓글을 달 수 있습니다");
        }

        Comment comment = Comment.builder()
                .content(reqCommentDTO.getContent())
                .member(member)
                .post(post)
                .build();

        commentRepository.save(comment);

        return true;
    }

    @Override
    @Transactional
    public ResCommentDTO updateComment(Member member, ReqCommentDTO reqCommentDTO, Long commentId) {
        Comment comment = commentRepository.getReferenceById(commentId);

        if (!comment.getMember().getMemberId().equals(member.getMemberId())) {
            throw new RuntimeException("본인이 작성한 댓글만 수정이 가능합니다.");
        }

        comment.setContent(reqCommentDTO.getContent());
        commentRepository.save(comment);

        return commentMapper.toResCommentDto(comment);
    }

    @Override
    @Transactional
    public Boolean deleteComment(Member member, Long commentId) {
        Comment comment = commentRepository.getReferenceById(commentId);

        if(!comment.getMember().getMemberId().equals(member.getMemberId())){
            throw new RuntimeException("본인이 작성한 댓글만 삭제가 가능합니다.");
        }

        commentRepository.delete(comment);

        return true;
    }
}
