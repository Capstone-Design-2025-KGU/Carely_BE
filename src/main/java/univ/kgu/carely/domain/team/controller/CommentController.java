package univ.kgu.carely.domain.team.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.team.dto.request.ReqCreateCommentDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
import univ.kgu.carely.domain.team.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams/{teamId}/posts/{postId}")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    @Operation(summary = "댓글 작성 API", description = "댓글을 작성한다.")
    public ResponseEntity<Boolean> createComment(@PathVariable("teamId") Long teamId,
                                                    @PathVariable("postId") Long postId,
                                                    @RequestBody ReqCreateCommentDTO reqCreateCommentDTO) {
        Boolean comment = commentService.createComment(postId, reqCreateCommentDTO);

        return ResponseEntity.ok(comment);
    }

}
