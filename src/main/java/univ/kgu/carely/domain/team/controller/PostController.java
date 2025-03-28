package univ.kgu.carely.domain.team.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
import univ.kgu.carely.domain.team.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<ResPostDTO> readPost(@PathVariable("postId") Long postId){
        postService.readPost(postId);

        return ResponseEntity.ok(null);
    }
}
