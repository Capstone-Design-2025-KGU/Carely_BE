package univ.kgu.carely.domain.team.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.member.service.MemberService;
import univ.kgu.carely.domain.team.dto.request.ReqCreateTeamDTO;
import univ.kgu.carely.domain.team.dto.response.ResTeamOutlineDTO;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.entity.TeamMate;
import univ.kgu.carely.domain.team.entity.TeamRole;
import univ.kgu.carely.domain.team.repository.team.TeamMateRepository;
import univ.kgu.carely.domain.team.repository.team.TeamRepository;
import univ.kgu.carely.domain.team.service.TeamService;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private static final int SEARCH_RANGE = 2000;

    private final MemberService memberService;
    private final TeamRepository teamRepository;
    private final TeamMateRepository teamMateRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Boolean createTeam(Member member, ReqCreateTeamDTO reqCreateTeamDTO) {
        Team team = Team.builder()
                .teamName(reqCreateTeamDTO.getTeamName())
                .address(reqCreateTeamDTO.getAddress())
                .build();

        teamRepository.save(team);

        TeamMate teamMate = TeamMate.builder()
                .member(member)
                .team(team)
                .role(TeamRole.LEADER)
                .build();

        teamMateRepository.save(teamMate);

        return true;
    }

    @Override
    @Transactional
    public Boolean joinTeam(Member member, Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(()->
                new RuntimeException("해당 그룹이 존재하지 않습니다."));

        Boolean alreadyExist = teamMateRepository.existsByTeamAndMember(team, member);

        if(alreadyExist){
            return false;
        }

        TeamMate teamMate = TeamMate.builder()
                .team(team)
                .member(member)
                .role(TeamRole.MATE)
                .build();

        teamMateRepository.save(teamMate);

        return true;
    }

    @Override
    @Transactional
    public Boolean exitTeam(Member member, Long teamId) {
        Team team = teamRepository.getReferenceById(teamId);

        TeamMate teamMate = teamMateRepository.findByTeamAndMember(team, member).orElseThrow(()->
                new RuntimeException("소속이 아닌 그룹을 탈퇴할 수 없습니다."));

        if(teamMate.getRole().equals(TeamRole.LEADER)){
            throw new RuntimeException("그룹장은 그룹을 탈퇴할 수 없습니다.");
        }

        teamMateRepository.delete(teamMate);

        return true;
    }

    @Override
    @Transactional
    public Boolean closeTeam(Member member, Long teamId) {
        Team team = teamRepository.getReferenceById(teamId);

        TeamMate teamMate = teamMateRepository.findByTeamAndMember(team, member).orElseThrow(()->
                new RuntimeException("소속이 아닌 그룹을 탈퇴할 수 없습니다."));

        if(!teamMate.getRole().equals(TeamRole.LEADER)){
            throw new RuntimeException("그룹장만 그룹을 해체할 수 있습니다.");
        }

        teamRepository.delete(team);

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResTeamOutlineDTO> searchNeighbor(Member member, Pageable pageable) {
        member = memberRepository.findById(member.getMemberId()).orElseThrow();

        return teamRepository.findTeamOutlineWithinDistance(member, SEARCH_RANGE, pageable);
    }

}
