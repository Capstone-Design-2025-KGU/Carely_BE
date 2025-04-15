package univ.kgu.carely.domain.member.repository.impl;

import static univ.kgu.carely.domain.meet.entity.QMeeting.meeting;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.meet.entity.QMeeting;
import univ.kgu.carely.domain.member.dto.response.ResMemberMapDTO;
import univ.kgu.carely.domain.member.dto.response.ResMembersRecommendedDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.entity.QMember;
import univ.kgu.carely.domain.member.repository.CustomMemberRepository;

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

    @Override
    public Page<ResMembersRecommendedDTO> findRecommendedMembers(int meter, Member my, Pageable pageable) {
        NumberTemplate<Double> distance = Expressions.numberTemplate(Double.class,
                "ST_DISTANCE_SPHERE({0}, {1})", my.getAddress().getLocation(), member.address.location);

        NumberTemplate<Integer> withTime = Expressions.numberTemplate(Integer.class, "");

        BooleanTemplate isNearby = Expressions.booleanTemplate("ST_CONTAINS(ST_BUFFER({0}, {1}), {2})",
                my.getAddress().getLocation(), meter, member.address.location);

        List<ResMembersRecommendedDTO> resMembersRecommendedDTOS;
        Long total;

        // FAMILY인 경우 약속을 받기만 가능함.
        if(my.getMemberType().equals(MemberType.FAMILY)){
            BooleanExpression findNearbyVisibleNotFamilyType = member.isVisible
                    .and(member.isVerified)
                    .and(member.memberType.ne(MemberType.FAMILY))
                    .and(isNearby);

            resMembersRecommendedDTOS = jpaQueryFactory.select(
                            Projections.fields(ResMembersRecommendedDTO.class,
                                    member.memberId,
                                    member.name,
                                    member.profileImage,
                                    member.memberType,
                                    distance.as("distance")
                            ))
                    .from(member)
                    .where(findNearbyVisibleNotFamilyType)
                    .orderBy(distance.asc())
                    .limit(pageable.getPageSize())
                    .offset(pageable.getOffset())
                    .fetch();

            total = jpaQueryFactory.select(member.memberId.count())
                    .from(member)
                    .where(findNearbyVisibleNotFamilyType)
                    .fetchOne();
        } else {
            BooleanExpression findNearbyVisibleFamilyType = member.isVisible
                    .and(member.isVerified)
                    .and(member.memberType.eq(MemberType.FAMILY))
                    .and(isNearby);

            resMembersRecommendedDTOS = jpaQueryFactory.select(
                            Projections.fields(ResMembersRecommendedDTO.class,
                                    member.memberId,
                                    member.name,
                                    member.profileImage,
                                    member.memberType,
                                    distance.as("distance")
                            ))
                    .from(member)
                    .where(findNearbyVisibleFamilyType)
                    .orderBy(distance.asc())
                    .limit(pageable.getPageSize())
                    .offset(pageable.getOffset())
                    .fetch();

            total = jpaQueryFactory.select(member.memberId.count())
                    .from(member)
                    .where(findNearbyVisibleFamilyType)
                    .fetchOne();
        }

        assert Objects.nonNull(total);
        return new PageImpl<>(resMembersRecommendedDTOS, pageable, total);
    }
}
