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
import univ.kgu.carely.domain.common.embeded.address.Address;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.meet.entity.QMeeting;
import univ.kgu.carely.domain.member.dto.response.ResMemberMapDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
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
        List<ResMembersRecommendedDTO> resMembersRecommendedDTOS;
        Long total;

        QMeeting mt = new QMeeting("mt");

        NumberTemplate<Double> distance = Expressions.numberTemplate(Double.class,
                "ST_DISTANCE_SPHERE({0}, {1})", my.getAddress().getLocation(), member.address.location);

        NumberTemplate<Integer> withTime = Expressions.numberTemplate(Integer.class,
                "SUM(TIMESTAMPDIFF(MINUTE, {0}, {1}))", mt.startTime, mt.endTime);

        BooleanTemplate isNearby = Expressions.booleanTemplate("ST_CONTAINS(ST_BUFFER({0}, {1}), {2})",
                my.getAddress().getLocation(), meter, member.address.location);

        // FAMILY인 경우 약속을 받기만 가능함.
        if(my.getMemberType().equals(MemberType.FAMILY)){
            BooleanExpression findNearbyVisibleNotFamilyType = member.isVisible
                    .and(member.isVerified)
                    .and(mt.receiver.eq(my)
                            .or(mt.receiver.isNull()))
                    .and(member.memberType.ne(MemberType.FAMILY))
                    .and(isNearby);

            resMembersRecommendedDTOS = jpaQueryFactory.select(
                            Projections.fields(ResMembersRecommendedDTO.class,
                                    member.memberId,
                                    member.name,
                                    member.profileImage,
                                    member.memberType,
                                    distance.as("distance"),
                                    withTime.as("withTime")
                            ))
                    .from(member)
                    .leftJoin(mt).on(member.eq(mt.sender))
                    .where(findNearbyVisibleNotFamilyType)
                    .groupBy(mt.sender, member)
                    .orderBy(distance.asc())
                    .limit(pageable.getPageSize())
                    .offset(pageable.getOffset())
                    .fetch();

            total = jpaQueryFactory.select(member.count())
                    .from(member)
                    .leftJoin(mt).on(member.eq(mt.sender))
                    .where(findNearbyVisibleNotFamilyType)
                    .fetchOne();

        } else { // FAMILY가 아닌 경우 약속 요청만 가능함.
            BooleanExpression findNearbyVisibleFamilyType = member.isVisible
                    .and(member.isVerified)
                    .and(mt.sender.eq(my)
                            .or(mt.sender.isNull()))
                    .and(member.memberType.eq(MemberType.FAMILY))
                    .and(isNearby);

            resMembersRecommendedDTOS = jpaQueryFactory.select(
                            Projections.fields(ResMembersRecommendedDTO.class,
                                    member.memberId,
                                    member.name,
                                    member.profileImage,
                                    member.memberType,
                                    distance.as("distance"),
                                    withTime.as("withTime")
                            ))
                    .from(member)
                    .leftJoin(mt).on(member.eq(mt.receiver))
                    .where(findNearbyVisibleFamilyType)
                    .groupBy(mt.sender, member)
                    .orderBy(distance.asc())
                    .limit(pageable.getPageSize())
                    .offset(pageable.getOffset()) // 자기 자신을 제외하도록 함.
                    .fetch();

            total = jpaQueryFactory.select(member.count())
                    .from(member)
                    .leftJoin(mt).on(member.eq(mt.receiver))
                    .where(findNearbyVisibleFamilyType)
                    .fetchOne();
        }

        assert Objects.nonNull(total);
        return new PageImpl<>(resMembersRecommendedDTOS, pageable, total);
    }

    @Override
    public ResMemberPublicInfoDTO getMemberPublicInfo(Long opponentId, Long selfId) {
        ResMemberPublicInfoDTO resMemberPublicInfoDTO;

        Member selfMember = jpaQueryFactory.select(Projections.fields(Member.class,
                        Projections.fields(Address.class,
                                member.address.location)
                                .as("address"),
                        member.memberType))
                .from(member)
                .where(member.memberId.eq(selfId))
                .fetchOne();

        resMemberPublicInfoDTO = jpaQueryFactory.select(
                        Projections.fields(ResMemberPublicInfoDTO.class,
                                member.memberId,
                                member.username,
                                member.name,
                                member.birth,
                                member.story,
                                member.memberType,
                                member.profileImage,
                                member.createdAt,
                                Expressions.numberTemplate(Double.class,
                                        "ST_DISTANCE_SPHERE({0}, {1})",
                                        selfMember.getAddress().getLocation(), member.address.location).as("distance"),
                                Projections.fields(Address.class,
                                                member.address.province,
                                                member.address.city,
                                                member.address.district,
                                                member.address.details,
                                                member.address.latitude,
                                                member.address.longitude)
                                        .as("address"),
                                member.skill))
                .from(member)
                .where(member.memberId.eq(opponentId))
                .fetchOne();

        Integer withTime;

        if(selfMember.getMemberType().equals(MemberType.FAMILY)) {
            withTime = jpaQueryFactory.select(Expressions.numberTemplate(Integer.class,
                            "SUM(TIMESTAMPDIFF(MINUTE, {0}, {1}))",
                            meeting.startTime, meeting.endTime))
                    .from(meeting)
                    .where(meeting.sender.memberId.eq(opponentId)
                            .and(meeting.receiver.memberId.eq(selfId)))
                    .fetchOne();
        } else {
            withTime = jpaQueryFactory.select(Expressions.numberTemplate(Integer.class,
                            "SUM(TIMESTAMPDIFF(MINUTE, {0}, {1}))",
                            meeting.startTime, meeting.endTime))
                    .from(meeting)
                    .where(meeting.receiver.memberId.eq(opponentId)
                            .and(meeting.sender.memberId.eq(selfId)))
                    .fetchOne();
        }

        resMemberPublicInfoDTO.setWithTime(Objects.requireNonNullElse(withTime,0)); // 함께한 시간이 없는 경우 0으로 return
        return resMemberPublicInfoDTO;
    }

    @Override
    public ResMemberPrivateInfoDTO getMemberPrivateInfo(Long memberId) {
        return jpaQueryFactory.select(Projections.fields(ResMemberPrivateInfoDTO.class,
                member.memberId,
                member.username,
                member.name,
                member.phoneNumber,
                member.birth,
                member.story,
                member.memberType,
                member.isVisible,
                member.isVerified,
                member.profileImage,
                member.createdAt,
                Expressions.numberTemplate(Integer.class,
                        "SUM(TIMESTAMPDIFF(MINUTE, {0}, {1}))",
                        meeting.startTime, meeting.endTime)
                        .as("withTimeSum"),
                member.address,
                member.skill))
                .from(member)
                .leftJoin(member.sendMeetings, meeting)
                .where(member.memberId.eq(memberId))
                .fetchOne();
    }

}
