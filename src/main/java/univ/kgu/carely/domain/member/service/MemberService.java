package univ.kgu.carely.domain.member.service;

import java.util.List;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.map.dto.request.ReqViewPortInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;

public interface MemberService {
    List<ResMemberPublicInfoDTO> searchNeighborMember(Long memberId, ReqViewPortInfoDTO viewPortDTO,
                                                      MemberType memberType);
}
