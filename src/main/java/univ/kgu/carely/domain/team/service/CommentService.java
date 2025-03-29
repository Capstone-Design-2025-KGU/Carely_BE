package univ.kgu.carely.domain.team.service;

import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.team.dto.request.ReqCommentDTO;
import univ.kgu.carely.domain.team.dto.response.ResCommentDTO;
import univ.kgu.carely.domain.team.entity.Comment;

public interface CommentService {

    @Transactional
    Boolean createComment(Long postId, ReqCommentDTO reqCommentDTO);

    Boolean updateComment(ReqCommentDTO reqCommentDTO, Long commentId);

    Boolean deleteComment(Long commentId);

    ResCommentDTO toResCommentDTO(Comment comment);
}
