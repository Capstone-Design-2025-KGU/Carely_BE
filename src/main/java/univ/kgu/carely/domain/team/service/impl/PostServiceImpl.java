package univ.kgu.carely.domain.team.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.service.MemberService;
import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
<<<<<<< HEAD
import univ.kgu.carely.domain.team.dto.response.ResPostOutlineDTO;
=======
>>>>>>> 9731157 (feat : PostService에서 응답 전용 DTO를 반환하도록 수정)
import univ.kgu.carely.domain.team.entity.Post;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.repository.PostRepository;
import univ.kgu.carely.domain.team.repository.TeamMateRepository;
import univ.kgu.carely.domain.team.repository.TeamRepository;
import univ.kgu.carely.domain.team.service.CommentService;
import univ.kgu.carely.domain.team.service.PostService;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final MemberService memberService;
    private final CommentService commentService;
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;
    private final TeamMateRepository teamMateRepository;

    @Override
    @Transactional
    public ResPostDTO createPost(ReqCreatePostDTO createPostDTO, Long teamId) {
        Member member = memberService.currentMember();
        Team team = teamRepository.findById(teamId).orElseThrow();

        if(!teamMateRepository.existsByTeamAndMember(team, member)){
            throw new RuntimeException("본인이 속한 그룹에만 글을 작성할 수 있습니다.");
        }

        Post post = Post.builder()
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .member(member)
                .team(team)
                .build();

        post = postRepository.save(post);

        return toResPostDTO(post);
    }

    @Override
    @Transactional(readOnly = true)
    public ResPostDTO readPost(Long postId) {
        Member member = memberService.currentMember();
        Post post = postRepository.findById(postId).orElseThrow();
        Team team = post.getTeam();

        if(!teamMateRepository.existsByTeamAndMember(team, member)) {
            throw new RuntimeException("다른 그룹의 게시글을 볼 수 없습니다.");
        }

        return toResPostDTO(post);
    }

    @Override
    public ResPostDTO toResPostDTO(Post post) {
        return ResPostDTO.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .writer(memberService.toResMemberSmallInfoDTO(post.getMember()))
                .resCommentDTOS(post.getComments().stream().map(commentService::toResCommentDTO).toList())
                .build();
    }

    @Override
    @Transactional
    public ResPostDTO updatePost(ReqUpdatePostDTO updatePostDTO, Long postId) {
        Member member = memberService.currentMember();
        Post post = postRepository.findById(postId).orElseThrow();

        if(!post.getMember().getMemberId().equals(member.getMemberId())) {
            throw new RuntimeException("본인 게시글만 수정할 수 있습니다.");
        }

        post.setTitle(updatePostDTO.getTitle());
        post.setContent(updatePostDTO.getContent());

        post = postRepository.save(post);

        return toResPostDTO(post);
    }

    @Override
    @Transactional
    public Boolean deletePost(Long postId) {
        Member member = memberService.currentMember();
        Post post = postRepository.findById(postId).orElseThrow();

        if(!post.getMember().getMemberId().equals(member.getMemberId())){
            throw new RuntimeException("본인 게시글만 삭제가 가능합니다.");
        }

        postRepository.delete(post);

        return true;
    }

    @Override
    @Transactional
    public Page<ResPostOutlineDTO> readPagedPost(String query, Long teamId, Pageable pageable) {
        Member member = memberService.currentMember();
        Team team = teamRepository.findById(teamId).orElseThrow();

        if(!teamMateRepository.existsByTeamAndMember(team, member)) {
            throw new RuntimeException("본인이 속한 그룹의 게시글만 볼 수 있습니다.");
        }

        return postRepository.findPostOutlineBy(query, team, pageable);
    }

}
