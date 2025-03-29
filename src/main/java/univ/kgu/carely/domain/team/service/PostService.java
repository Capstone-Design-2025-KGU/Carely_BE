package univ.kgu.carely.domain.team.service;

import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
import univ.kgu.carely.domain.team.entity.Post;

public interface PostService {

    /**
     * 게시글을 작성한다.
     *
     * @param createPostDTO 작성하려는 게시글의 정보
     * @param teamId        게시하려고 하는 그룹 ID
     * @return 작성된 게시글
     */
    ResPostDTO createPost(ReqCreatePostDTO createPostDTO, Long teamId);

    /**
     * 단일 게시글을 조회한다.
     *
     * @param postId 조회하려고 하는 게시글 ID
     * @return 조회된 게시글
     */
    ResPostDTO readPost(Long postId);

    /**
     * 게시글을 수정한다.
     * 
     * @param updatePostDTO 수정할 내용을 담은 DTO
     * @param postId        수정할 게시글 ID
     * @return 수정된 게시글
     */
    ResPostDTO updatePost(ReqUpdatePostDTO updatePostDTO, Long postId);

    /**
     * 게시글을 삭제한다.
     * 
     * @param postId 삭제할 게시글 ID
     * @return 삭제 성공 여부
     */
    Boolean deletePost(Long postId);

    /**
     * 게시글을 응답용 DTO로 변환한다.
     *
     * @param post 변환하려고 하는 게시글 엔티티
     * @return 게시글 응답용 DTO
     */
    ResPostDTO toResPostDTO(Post post);
}
