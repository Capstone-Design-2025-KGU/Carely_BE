package univ.kgu.carely.domain.team.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.service.MemberService;
import univ.kgu.carely.domain.member.util.MemberMapper;
import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostOutlineDTO;
import univ.kgu.carely.domain.team.entity.Post;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.repository.post.PostRepository;
import univ.kgu.carely.domain.team.repository.team.TeamMateRepository;
import univ.kgu.carely.domain.team.repository.team.TeamRepository;
import univ.kgu.carely.domain.team.service.CommentService;
import univ.kgu.carely.domain.team.service.PostService;
import univ.kgu.carely.domain.team.util.PostMapper;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TeamRepository teamRepository;
    private final TeamMateRepository teamMateRepository;

    private final PostMapper postMapper;

    @Override
    @Transactional
    public ResPostDTO createPost(Member member, ReqCreatePostDTO createPostDTO, Long teamId) {
        Team team = teamRepository.getReferenceById(teamId);

        if(!teamMateRepository.existsByTeamAndMember(team, member)){
            throw new RuntimeException("본인이 속한 그룹에만 글을 작성할 수 있습니다.");
        }
        Post post = postMapper.toEntity(createPostDTO, member, team);

        post = postRepository.save(post);

        return postMapper.toResPostDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public ResPostDTO readPost(Member member, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        Team team = post.getTeam();

        if(!teamMateRepository.existsByTeamAndMember(team, member)) {
            throw new RuntimeException("다른 그룹의 게시글을 볼 수 없습니다.");
        }

        return postMapper.toResPostDto(post);
    }

    @Override
    @Transactional
    public ResPostDTO updatePost(Member member, ReqUpdatePostDTO updatePostDTO, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        if(!post.getMember().getMemberId().equals(member.getMemberId())) {
            throw new RuntimeException("본인 게시글만 수정할 수 있습니다.");
        }

        postMapper.updateEntity(post, updatePostDTO);

        post = postRepository.save(post);

        return postMapper.toResPostDto(post);
    }

    @Override
    @Transactional
    public Boolean deletePost(Member member, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        if(!post.getMember().getMemberId().equals(member.getMemberId())){
            throw new RuntimeException("본인 게시글만 삭제가 가능합니다.");
        }

        postRepository.delete(post);

        return true;
    }

    @Override
    @Transactional
    public Page<ResPostOutlineDTO> readPagedPost(Member member, String query, Long teamId, Pageable pageable) {
        Team team = teamRepository.getReferenceById(teamId);

        if(!teamMateRepository.existsByTeamAndMember(team, member)) {
            throw new RuntimeException("본인이 속한 그룹의 게시글만 볼 수 있습니다.");
        }

        return postRepository.findPostOutlineBy(query, team, pageable);
    }

}
