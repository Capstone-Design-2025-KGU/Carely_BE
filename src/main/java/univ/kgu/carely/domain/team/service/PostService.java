package univ.kgu.carely.domain.team.service;

import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.entity.Post;

public interface PostService {
    Post createPost(ReqCreatePostDTO createPostDTO);

    Boolean deletePost(Long postId);

    Post updatePost(ReqUpdatePostDTO updatePostDTO);
}
