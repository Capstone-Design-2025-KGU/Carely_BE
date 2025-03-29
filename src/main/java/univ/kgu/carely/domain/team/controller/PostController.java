package univ.kgu.carely.domain.team.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
import univ.kgu.carely.domain.team.service.PostService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams/{teamId}")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/{postId}")
    public ResponseEntity<ResPostDTO> readPost(@PathVariable("teamId") Long teamId,
                                               @PathVariable("postId") Long postId){


        return ResponseEntity.ok(null);
    }
}
