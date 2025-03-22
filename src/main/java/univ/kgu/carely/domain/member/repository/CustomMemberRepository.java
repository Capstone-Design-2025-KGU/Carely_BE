package univ.kgu.carely.domain.member.repository;

import java.math.BigDecimal;
import java.util.List;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.map.dto.request.ReqCoordinationDTO;
import univ.kgu.carely.domain.map.dto.request.ReqViewPortInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;

public interface CustomMemberRepository {
    Member findByName(String name);

    List<ResMemberPublicInfoDTO> findAllWithinDistance(BigDecimal lat, BigDecimal lng, int meter);

    Double checkVerifiedPlaceWithGPS(Long memberId, ReqCoordinationDTO reqCoordinationDTO);
}
