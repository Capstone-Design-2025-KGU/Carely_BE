package univ.kgu.carely.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import univ.kgu.carely.domain.member.dto.response.ResMemberMapDTO;
import univ.kgu.carely.domain.member.entity.QMember;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QMember member = QMember.member;

    /**
     * 입력한 위도/경도를 기준으로 해당 범위 내에 존재하는 모든 멤버를 찾는다.
     *
     * @param meter 중심으로부터 확인하려고 하는 범위
     * @return 범위 내의 모든 멤버
     */
    @Override
    public List<ResMemberMapDTO> findAllWithinDistance(String query, Point p, int meter) {
        BooleanTemplate isNearby = Expressions.booleanTemplate("ST_CONTAINS(ST_BUFFER({0}, {1}), {2})",
                p, 2000, member.address.location);

        return jpaQueryFactory.select(
                        Projections.fields(ResMemberMapDTO.class,
                                member.memberId,
                                member.memberType,
                                member.address.latitude.as("lat"),
                                member.address.longitude.as("lng")
                        ))
                .from(member)
                .where(member.isVerified
                        .and(member.isVisible)
                        .and(member.name.contains(query))
                        .and(isNearby)
                )
                .fetch();
    }

    @Override
    public Double checkVerifiedPlaceWithGPS(Long memberId, Point point) {
        NumberTemplate<Double> distance = Expressions.numberTemplate(Double.class,
                "ST_Distance_Sphere({0}, {1})", point, member.address.location);

        return jpaQueryFactory.select(distance)
                .from(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();
    }
}
