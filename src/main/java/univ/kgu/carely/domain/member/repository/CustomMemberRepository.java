package univ.kgu.carely.domain.member.repository;

import java.math.BigDecimal;
import java.util.List;
import univ.kgu.carely.domain.map.dto.request.ReqCoordinationDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;

public interface CustomMemberRepository {
    Member findByName(String name);

    /**
     * 입력한 위도/경도를 기준으로 해당 범위 내에 존재하는 모든 멤버를 찾는다.
     *
     * @param lat        중심이 되는 위치의 위도
     * @param lng        중심이 되는 위치의 경도
     * @param meter      중심으로부터 확인하려고 하는 범위
     * @return 범위 내의 모든 멤버
     */
    List<ResMemberPublicInfoDTO> findAllWithinDistance(String query, BigDecimal lat, BigDecimal lng, int meter);

    /**
     * 위도/경도 좌표를 가지고 거리를 측정한다.
     *
     * @param memberId           동네 인증을 하려고 하는 유저의 id값
     * @param reqCoordinationDTO 동네 인증이 진행되는 GPS 값
     * @return 등록된 주소의 좌표와 요청 좌표의 차이 (단위 m)
     */
    Double checkVerifiedPlaceWithGPS(Long memberId, ReqCoordinationDTO reqCoordinationDTO);
}
