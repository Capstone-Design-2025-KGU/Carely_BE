package univ.kgu.carely.domain.member.service;

import java.util.List;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.map.dto.request.ReqViewPortInfoDTO;
import univ.kgu.carely.domain.member.dto.request.ReqMemberCreateDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;

public interface MemberService {

    /**
     * 인증된 동네 기준 주변 이웃을 검색한다.
     * @param memberId    본인 아이디
     * @param viewPortDTO 현재 뷰포트 정보
     * @param memberType  검색하려고 하는 멤버 타입
     * @return 검색된 데이터
     */
    List<ResMemberPublicInfoDTO> searchNeighborMember(Long memberId, ReqViewPortInfoDTO viewPortDTO,
                                                      MemberType memberType);

    ResMemberPrivateInfoDTO createMember(ReqMemberCreateDTO reqMemberCreateDTO);
}
