package univ.kgu.carely.domain.team.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostOutlineDTO;
import univ.kgu.carely.domain.team.service.PostService;

// ToDo 전체적으로 엔드포인트 수정이 필요할 것 같음.

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams/{teamId}/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("")
    @Operation(summary = "그룹 내 게시글 조회 API")
    public ResponseEntity<Page<ResPostOutlineDTO>> readPostList(@PathVariable("teamId") Long teamId,
                                                                @RequestParam(value = "query", defaultValue = "") String query,
                                                                @PageableDefault Pageable pageable,
                                                                @AuthenticationPrincipal(expression = "member") Member member) {
        Page<ResPostOutlineDTO> outlineDTOS = postService.readPagedPost(member, query, teamId, pageable);

        return ResponseEntity.ok(outlineDTOS);
    }

    @GetMapping("/{postId}")
    @Operation(summary = "단일 게시글 조회 API")
    public ResponseEntity<ResPostDTO> readPost(@PathVariable("teamId") Long teamId,
                                               @PathVariable("postId") Long postId,
                                               @AuthenticationPrincipal(expression = "member") Member member) {
        ResPostDTO resPostDTO = postService.readPost(member, postId);

        return ResponseEntity.ok(resPostDTO);
    }

    @PostMapping("")
    @Operation(summary = "게시글 작성 API")
    public ResponseEntity<ResPostDTO> createPost(@PathVariable("teamId") Long teamId,
                                                 @RequestBody ReqCreatePostDTO reqCreatePostDTO,
                                                 @AuthenticationPrincipal(expression = "member") Member member) {
        ResPostDTO post = postService.createPost(member, reqCreatePostDTO, teamId);

        return ResponseEntity.ok(post);
    }

    @PutMapping("/{postId}")
    @Operation(summary = "게시글 수정 API")
    public ResponseEntity<ResPostDTO> updatePost(@PathVariable("teamId") Long teamId,
                                                 @PathVariable("postId") Long postId,
                                                 @RequestBody ReqUpdatePostDTO reqUpdatePostDTO,
                                                 @AuthenticationPrincipal(expression = "member") Member member) {
        ResPostDTO resPostDTO = postService.updatePost(member, reqUpdatePostDTO, postId);

        return ResponseEntity.ok(resPostDTO);
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제 API")
    public ResponseEntity<Boolean> deletePost(@PathVariable("teamId") Long teamId,
                                              @PathVariable("postId") Long postId,
                                              @AuthenticationPrincipal(expression = "member") Member member) {
        Boolean success = postService.deletePost(member, postId);

        return ResponseEntity.ok(success);
    }

}
