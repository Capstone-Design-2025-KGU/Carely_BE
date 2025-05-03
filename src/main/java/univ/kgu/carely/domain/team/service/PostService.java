package univ.kgu.carely.domain.team.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostOutlineDTO;
import univ.kgu.carely.domain.team.entity.Post;

public interface PostService {

    /**
     * 게시글을 작성한다.
     *
     * @param member
     * @param createPostDTO 작성하려는 게시글의 정보
     * @param teamId        게시하려고 하는 그룹 ID
     * @return 작성된 게시글
     */
    ResPostDTO createPost(Member member, ReqCreatePostDTO createPostDTO, Long teamId);

    /**
     * 단일 게시글을 조회한다.
     *
     * @param member
     * @param postId 조회하려고 하는 게시글 ID
     * @return 조회된 게시글
     */
    ResPostDTO readPost(Member member, Long postId);

    /**
     * 게시글을 수정한다.
     *
     * @param member
     * @param updatePostDTO 수정할 내용을 담은 DTO
     * @param postId        수정할 게시글 ID
     * @return 수정된 게시글
     */
    ResPostDTO updatePost(Member member, ReqUpdatePostDTO updatePostDTO, Long postId);

    /**
     * 게시글을 삭제한다.
     *
     * @param member
     * @param postId 삭제할 게시글 ID
     * @return 삭제 성공 여부
     */
    Boolean deletePost(Member member, Long postId);

    @Transactional
    Page<ResPostOutlineDTO> readPagedPost(Member member, String query, Long teamId, Pageable pageable);
}
