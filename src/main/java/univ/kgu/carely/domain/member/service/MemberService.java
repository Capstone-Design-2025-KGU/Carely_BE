package univ.kgu.carely.domain.member.service;

import java.util.List;
import univ.kgu.carely.domain.map.dto.request.ReqCoordinationDTO;
import univ.kgu.carely.domain.member.dto.request.ReqMemberCreateDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;

public interface MemberService {

    Member currentMember();

    /**
     * 인증된 동네 기준 주변 이웃을 검색한다.
     *
     * @return 검색된 데이터
     */
    List<ResMemberPublicInfoDTO> searchNeighborMember();

    /**
     * 회원가입을 진행한다.
     *
     * @param reqMemberCreateDTO 회원가입에 필요한 데이터
     * @return 가입된 회원 정보를 반환한다.
     */
    ResMemberPrivateInfoDTO createMember(ReqMemberCreateDTO reqMemberCreateDTO);

    /**
     * 로그인에 필요한 username의 중복검사를 진행한다.
     *
     * @param username 중복검사를 진행하려고 하는 username
     * @return 중복된 아이디면 true, 그렇지 않으면 false
     */
    Boolean isDuplicatedUsername(String username);

    /**
     * 휴대전화번호의 중복검사를 진행한다.
     *
     * @param phoneNumber 중복검사를 하려는 전화번호 ex)010-0000-0000
     * @return 중복된 전화번호면 true, 아니라면 false
     */
    Boolean isDuplicatedPhoneNumber(String phoneNumber);

    /**
     * 이웃 인증을 진행한다.
     *
     * @param reqCoordinationDTO GPS 기반 현재 위치 정보
     * @return 이웃 인증 성공 여부. 성공시 true, 실패시 false
     */
    Boolean verifyNeighbor(ReqCoordinationDTO reqCoordinationDTO);

    ResMemberPrivateInfoDTO getPrivateInfo(Long memberId);
}
