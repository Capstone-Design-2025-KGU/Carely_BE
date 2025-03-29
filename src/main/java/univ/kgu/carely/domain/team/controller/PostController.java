package univ.kgu.carely.domain.team.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostOutlineDTO;
import univ.kgu.carely.domain.team.service.PostService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams/{teamId}")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    @Operation(summary = "그룹 내 게시글 조회 API")
    public ResponseEntity<Page<ResPostOutlineDTO>> readPostList(@PathVariable("teamId") Long teamId) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/posts/{postId}")
    @Operation(summary = "단일 게시글 조회 API")
    public ResponseEntity<ResPostDTO> readPost(@PathVariable("teamId") Long teamId,
                                               @PathVariable("postId") Long postId) {

        return ResponseEntity.ok(null);
    }

    @PostMapping("/posts")
    @Operation(summary = "게시글 작성 API")
    public ResponseEntity<ResPostDTO> createPost(@PathVariable("teamId") Long teamId) {

        return ResponseEntity.ok(null);
    }

    @PutMapping("/posts/{postId}")
    @Operation(summary = "게시글 수정 API")
    public ResponseEntity<ResPostDTO> updatePost(@PathVariable("teamId") Long teamId,
                                                 @PathVariable("postId") Long postId){

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/post/{postId}")
    @Operation(summary = "게시글 삭제 API")
    public ResponseEntity<Boolean> deletePost(@PathVariable("teamId") Long teamId,
                                              @PathVariable("postId") Long postId){

        return ResponseEntity.ok(null);
    }

}
