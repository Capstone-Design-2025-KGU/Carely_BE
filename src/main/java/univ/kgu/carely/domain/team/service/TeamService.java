package univ.kgu.carely.domain.team.service;

import univ.kgu.carely.domain.team.dto.request.ReqCreateTeamDTO;

public interface TeamService {

    /**
     * 그룹 생성
     *
     * @param reqCreateTeamDTO 그룹 생성에 필요한 정보를 담은 DTO
     * @return 그룹 생성 성공 여부
     */
    Boolean createTeam(ReqCreateTeamDTO reqCreateTeamDTO);

    /**
     * 그룹 가입
     *
     * @param teamId 가입하려고 하는 그룹의 id
     * @return 그룹 가입 성공 여부
     */
    Boolean joinTeam(Long teamId);

    /**
     * 그룹 탈퇴
     *
     * @param teamId 탈퇴하려는 그룹의 id
     * @return 그룹 탈퇴 성공 여부
     */
    Boolean exitTeam(Long teamId);

    /**
     * 그룹 폐쇄
     * 
     * @param teamId 폐쇄하려는 그룹의 id
     * @return 그룹 폐쇄 성공 여부
     */
    Boolean closeTeam(Long teamId);
}
