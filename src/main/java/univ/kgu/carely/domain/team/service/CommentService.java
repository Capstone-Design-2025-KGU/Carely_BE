package univ.kgu.carely.domain.team.service;

import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.team.dto.request.ReqCommentDTO;
import univ.kgu.carely.domain.team.dto.response.ResCommentDTO;
import univ.kgu.carely.domain.team.entity.Comment;

public interface CommentService {

    /**
     * 댓글을 작성한다.
     *
     * @param member
     * @param postId        댓글을 작성하려고 하는 게시글 ID
     * @param reqCommentDTO 작성하려고 하는 댓글의 내용을 담은 DTO
     * @return 댓글 작성 성공 여부
     */
    Boolean createComment(Member member, Long postId, ReqCommentDTO reqCommentDTO);

    /**
     * 댓글을 수정한다.
     *
     * @param member
     * @param reqCommentDTO 수정하려고 하는 댓글의 내용
     * @param commentId     수정하려고 하는 댓글 ID
     * @return 댓글 수정 성공 여부
     */
    ResCommentDTO updateComment(Member member, ReqCommentDTO reqCommentDTO, Long commentId);

    /**
     * 댓글을 삭제한다.
     *
     * @param member
     * @param commentId 삭제하려고 하는 댓글 ID
     * @return 댓글 삭제 성공 여부
     */
    Boolean deleteComment(Member member, Long commentId);
}
