package univ.kgu.carely.domain.team.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.entity.Post;
import univ.kgu.carely.domain.team.repository.PostRepository;
import univ.kgu.carely.domain.team.service.PostService;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post createPost(ReqCreatePostDTO createPostDTO) {
        Post post = Post.builder()
                .build();

        return post;
    }

    @Override
    public Boolean deletePost(Long postId) {
        return true;
    }

    @Override
    public Post updatePost(ReqUpdatePostDTO updatePostDTO) {
        Post post = Post.builder()
                .build();

        return post;
    }

}
