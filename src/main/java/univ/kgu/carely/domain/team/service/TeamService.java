package univ.kgu.carely.domain.team.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.team.dto.request.ReqCreateTeamDTO;
import univ.kgu.carely.domain.team.dto.response.ResTeamOutlineDTO;

public interface TeamService {

    /**
     * 그룹 생성
     *
     * @param member
     * @param reqCreateTeamDTO 그룹 생성에 필요한 정보를 담은 DTO
     * @return 그룹 생성 성공 여부
     */
    ResTeamOutlineDTO createTeam(Member member, ReqCreateTeamDTO reqCreateTeamDTO);

    /**
     * 그룹 가입
     *
     * @param member
     * @param teamId 가입하려고 하는 그룹의 id
     * @return 그룹 가입 성공 여부
     */
    Boolean joinTeam(Member member, Long teamId);

    /**
     * 그룹 탈퇴
     *
     * @param member
     * @param teamId 탈퇴하려는 그룹의 id
     * @return 그룹 탈퇴 성공 여부
     */
    Boolean exitTeam(Member member, Long teamId);

    /**
     * 그룹 폐쇄
     *
     * @param member
     * @param teamId 폐쇄하려는 그룹의 id
     * @return 그룹 폐쇄 성공 여부
     */
    Boolean closeTeam(Member member, Long teamId);

    Page<ResTeamOutlineDTO> searchNeighbor(Member member, Pageable pageable);

    List<ResTeamOutlineDTO> getMyTeams(Member auth);
}
