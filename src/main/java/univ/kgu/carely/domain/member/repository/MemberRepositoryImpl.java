package univ.kgu.carely.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import univ.kgu.carely.domain.common.embeded.Address;
import univ.kgu.carely.domain.map.dto.request.ReqCoordinationDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.entity.QMember;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QMember member = QMember.member;

    @Override
    public Member findByName(String name) {

        return jpaQueryFactory.select(member)
                .from(member)
                .where(member.name.eq(name))
                .fetchOne();
    }

    /**
     * 입력한 위도/경도를 기준으로 해당 범위 내에 존재하는 모든 멤버를 찾는다.
     *
     * @param lat        중심이 되는 위치의 위도
     * @param lng        중심이 되는 위치의 경도
     * @param meter      중심으로부터 확인하려고 하는 범위
     * @return 범위 내의 모든 멤버
     */
    @Override
    public List<ResMemberPublicInfoDTO> findAllWithinDistance(String query, BigDecimal lat, BigDecimal lng, int meter) {
        // MySQL에서 지원하는 위도/경도로 거리 비교하는 함수 이용
        NumberExpression<Double> distance = Expressions.numberTemplate(Double.class,
                "ST_Distance_Sphere(POINT({0}, {1}), POINT({2}, {3}))",
                lng, lat, member.address.longitude, member.address.latitude);

        return jpaQueryFactory.select(
                        Projections.fields(ResMemberPublicInfoDTO.class,
                                member.memberId,
                                member.username,
                                member.name,
                                member.birth,
                                member.story,
                                member.memberType,
                                member.profileImage,
                                member.createdAt,
                                distance.as("distance"),
                                Projections.fields(Address.class,
                                                member.address.province,
                                                member.address.city,
                                                member.address.district,
                                                member.address.latitude,
                                                member.address.longitude)
                                        .as("address"),
                                member.skill))
                .from(member)
                .where(member.isVerified
                        .and(member.isVisible)
                        .and(member.name.contains(query))
                        .and(distance.loe(meter)))
                .fetch();
    }

    @Override
    public Double checkVerifiedPlaceWithGPS(Long memberId, ReqCoordinationDTO reqCoordinationDTO) {
        NumberTemplate<Double> distance = Expressions.numberTemplate(Double.class,
                "ST_Distance_Sphere(POINT({0}, {1}), POINT({2}, {3}))",
                reqCoordinationDTO.getLng(), reqCoordinationDTO.getLat(), member.address.longitude,
                member.address.latitude);

        return jpaQueryFactory.select(distance)
                .from(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();
    }
}
