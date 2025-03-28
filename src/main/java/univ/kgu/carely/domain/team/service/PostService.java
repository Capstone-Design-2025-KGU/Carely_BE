package univ.kgu.carely.domain.team.service;

import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.entity.Post;

public interface PostService {
    Post createPost(ReqCreatePostDTO createPostDTO);

    Post readPost(Long postId);

    Post updatePost(ReqUpdatePostDTO updatePostDTO);

    Boolean deletePost(Long postId);
}
