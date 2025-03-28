package univ.kgu.carely.domain.team.service;

import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
import univ.kgu.carely.domain.team.entity.Post;

public interface PostService {
    ResPostDTO createPost(ReqCreatePostDTO createPostDTO);

    ResPostDTO readPost(Long postId);

    ResPostDTO toResPostDTO(Post post);

    ResPostDTO updatePost(ReqUpdatePostDTO updatePostDTO);

    Boolean deletePost(Long postId);
}
