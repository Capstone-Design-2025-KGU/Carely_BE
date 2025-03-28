package univ.kgu.carely.domain.team.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.kgu.carely.domain.team.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team")
public class PostController {

    private final PostService postService;

}
