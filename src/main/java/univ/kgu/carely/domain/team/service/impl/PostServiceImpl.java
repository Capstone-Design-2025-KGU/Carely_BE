package univ.kgu.carely.domain.team.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.service.MemberService;
import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.entity.Post;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.repository.PostRepository;
import univ.kgu.carely.domain.team.repository.TeamMateRepository;
import univ.kgu.carely.domain.team.repository.TeamRepository;
import univ.kgu.carely.domain.team.service.PostService;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final MemberService memberService;
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;
    private final TeamMateRepository teamMateRepository;

    @Override
    @Transactional
    public Post createPost(ReqCreatePostDTO createPostDTO) {
        Team team = teamRepository.findById(createPostDTO.getTeamId()).orElseThrow();
        Member member = memberService.currentMember();

        Post post = Post.builder()
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .member(member)
                .team(team)
                .build();

        post = postRepository.save(post);

        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public Post readPost(Long postId) {
        Member member = memberService.currentMember();
        Post post = postRepository.findById(postId).orElseThrow();
        Team team = post.getTeam();

        if(!teamMateRepository.existsByTeamAndMember(team, member)) {
            throw new RuntimeException("다른 그룹의 게시글을 볼 수 없습니다.");
        }

        return post;
    }

    @Override
    @Transactional
    public Post updatePost(ReqUpdatePostDTO updatePostDTO) {
        Member member = memberService.currentMember();
        Post post = postRepository.findById(updatePostDTO.getPostId()).orElseThrow();

        if(!post.getMember().getMemberId().equals(member.getMemberId())) {
            throw new RuntimeException("본인 게시글만 수정할 수 있습니다.");
        }

        post.setTitle(updatePostDTO.getTitle());
        post.setContent(updatePostDTO.getContent());

        post = postRepository.save(post);

        return post;
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

}
