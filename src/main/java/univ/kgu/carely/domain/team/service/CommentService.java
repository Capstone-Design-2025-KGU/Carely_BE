package univ.kgu.carely.domain.team.service;

import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.team.dto.request.ReqCreateCommentDTO;
import univ.kgu.carely.domain.team.dto.response.ResCommentDTO;
import univ.kgu.carely.domain.team.entity.Comment;

public interface CommentService {

    @Transactional
    Boolean createComment(Long postId, ReqCreateCommentDTO reqCreateCommentDTO);

    ResCommentDTO toResCommentDTO(Comment comment);
}
