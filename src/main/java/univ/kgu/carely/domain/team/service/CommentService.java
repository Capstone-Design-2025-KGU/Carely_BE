package univ.kgu.carely.domain.team.service;

import univ.kgu.carely.domain.team.dto.request.ReqCommentDTO;
import univ.kgu.carely.domain.team.dto.response.ResCommentDTO;
import univ.kgu.carely.domain.team.entity.Comment;

public interface CommentService {

    /**
     * 댓글을 작성한다.
     * 
     * @param postId        댓글을 작성하려고 하는 게시글 ID
     * @param reqCommentDTO 작성하려고 하는 댓글의 내용을 담은 DTO
     * @return 댓글 작성 성공 여부
     */
    Boolean createComment(Long postId, ReqCommentDTO reqCommentDTO);

    /**
     * 댓글을 수정한다.
     * 
     * @param reqCommentDTO 수정하려고 하는 댓글의 내용
     * @param commentId     수정하려고 하는 댓글 ID
     * @return 댓글 수정 성공 여부
     */
    Boolean updateComment(ReqCommentDTO reqCommentDTO, Long commentId);

    /**
     * 댓글을 삭제한다.
     *
     * @param commentId 삭제하려고 하는 댓글 ID
     * @return 댓글 삭제 성공 여부
     */
    Boolean deleteComment(Long commentId);

    /**
     * 댓글을 응답용 DTO로 변환한다.
     *
     * @param comment 변환하려고 하는 댓글 엔티티
     * @return 변환된 DTO
     */
    ResCommentDTO toResCommentDTO(Comment comment);
}
