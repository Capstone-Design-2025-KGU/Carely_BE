package univ.kgu.carely.domain.member.repository;

import java.util.List;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.member.dto.response.ResMemberMapDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMembersRecommendedDTO;
import univ.kgu.carely.domain.member.entity.Member;

public interface CustomMemberRepository {

    /**
     * 입력한 위도/경도를 기준으로 해당 범위 내에 존재하는 모든 멤버를 찾는다.
     *
     * @param meter      중심으로부터 확인하려고 하는 범위
     * @return 범위 내의 모든 멤버
     */
    List<ResMemberMapDTO> findAllWithinDistance(String query, Point p, int meter);

    /**
     * 위도/경도 좌표를 가지고 거리를 측정한다.
     *
     * @param memberId 동네 인증을 하려고 하는 유저의 id값
     * @param point    동네 인증이 진행되는 GPS 값
     * @return 등록된 주소의 좌표와 요청 좌표의 차이 (단위 m)
     */
    Double checkVerifiedPlaceWithGPS(Long memberId, Point point);

    Page<ResMembersRecommendedDTO> findRecommendedMembers(int meter, Member my, Pageable pageable);

    ResMemberPublicInfoDTO getMemberPublicInfo(Long opponentId, Long selfId);

    ResMemberPrivateInfoDTO getMemberPrivateInfo(Long memberId);
}
