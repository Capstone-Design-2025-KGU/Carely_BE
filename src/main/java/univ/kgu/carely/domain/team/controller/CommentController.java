package univ.kgu.carely.domain.team.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.team.dto.request.ReqCommentDTO;
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
                                                 @RequestBody ReqCommentDTO reqCommentDTO,
                                                 @AuthenticationPrincipal(expression = "member") Member member) {
        Boolean comment = commentService.createComment(member, postId, reqCommentDTO);

        return ResponseEntity.ok(comment);
    }

    @PutMapping("/comments/{commentId}")
    @Operation(summary = "댓글 수정 API", description = "댓글을 수정한다.")
    public ResponseEntity<Boolean> updateComment(@PathVariable("teamId") Long teamId,
                                                 @PathVariable("postId") Long postId,
                                                 @PathVariable("commentId") Long commentId,
                                                 @RequestBody ReqCommentDTO reqCommentDTO,
                                                 @AuthenticationPrincipal(expression = "member") Member member) {
        Boolean success = commentService.updateComment(member, reqCommentDTO, commentId);

        return ResponseEntity.ok(success);
    }

    @DeleteMapping("/comments/{commentId}")
    @Operation(summary = "댓글 삭제 API", description = "댓글을 삭제한다.")
    public ResponseEntity<Boolean> deleteComment(@PathVariable("teamId") Long teamId,
                                                 @PathVariable("postId") Long postId,
                                                 @PathVariable("commentId") Long commentId,
                                                 @AuthenticationPrincipal(expression = "member") Member member){
        Boolean success = commentService.deleteComment(member, commentId);

        return ResponseEntity.ok(success);
    }

}
