package univ.kgu.carely.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import univ.kgu.carely.domain.common.embeded.Address;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.map.dto.request.ReqViewPortInfoDTO;
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
     * @param viewPort   현재 보이는 뷰 포트 정보
     * @param memberType 검색하려고 하는 멤버 타입
     * @return 범위 내의 모든 멤버
     */
    @Override
    public List<ResMemberPublicInfoDTO> findAllWithinDistance(BigDecimal lat, BigDecimal lng, int meter,
                                                              ReqViewPortInfoDTO viewPort, MemberType memberType) {
        // MySQL에서 지원하는 위도/경도로 거리 비교하는 함수 이용
        NumberExpression<Double> distance = Expressions.numberTemplate(Double.class,
                "ST_Distance_Sphere(POINT({0}, {1}), POINT({2}, {3}))",
                lng, lat, member.address.longitude, member.address.latitude);

        BooleanExpression isInViewPort = member.address.longitude.between(viewPort.getLbLng(), viewPort.getRtLng())
                .and(member.address.latitude.between(viewPort.getLbLat(), viewPort.getRtLat()));

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
                                Projections.fields(Address.class,
                                                member.address.province,
                                                member.address.city,
                                                member.address.district,
                                                member.address.latitude,
                                                member.address.longitude)
                                        .as("address"),
                                member.skill))
                .from(member)
                .where(
                        member.isVerified
                                .and(member.isVisible)
                                .and(member.memberType.eq(memberType))
                                .and(isInViewPort)
                                .and(distance.loe(meter)))
                .fetch();
    }
}
