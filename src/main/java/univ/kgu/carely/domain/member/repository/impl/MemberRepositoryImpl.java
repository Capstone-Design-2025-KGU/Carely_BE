package univ.kgu.carely.domain.member.repository.impl;

import static univ.kgu.carely.domain.meet.entity.QMeeting.meeting;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.common.embeded.address.Address;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.meet.entity.MeetingStatus;
import univ.kgu.carely.domain.meet.entity.QMeeting;
import univ.kgu.carely.domain.member.dto.response.ResMemberMapDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMembersRecommendedDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.entity.QMember;
import univ.kgu.carely.domain.member.repository.CustomMemberRepository;
import univ.kgu.carely.util.UnitTrans;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QMember member = QMember.member;

    /**
     * 로그인된 사용자의 등록된 주소 기준으로 해당 범위 내에 존재하는 모든 멤버를 찾는다.
     *
     * @param meter 중심으로부터 확인하려고 하는 범위
     * @param self 조회를 요청한 멤버
     * @return 범위 내의 모든 멤버
     */
    @Override
    public List<ResMemberMapDTO> findAllWithinDistance(String query, int meter, Member self) {
        /*
        SELECT
            m.member_id, m.member_type, m.name, m.skill, m.lat, m.lng,
            SUM(TIMESTAMPDIFF(HOUR, mt.start_time, mt.end_time)) AS withTime,
            ST_DISTANCE_SPHERE(m.location, self.location) AS distance
        FROM
            member m
        LEFT JOIN
            meeting mt
            -- self 의 member_type 이 FAMILY 가 아닌 경우 qMember1 = mt.receiver, qMember2 = mt.sender
            ON mt.receiver.member_id = m.member_id
            -- self 의 member_type 이 FAMILY 인 경우 qMember1 = mt.sender, qMember2 = mt.receiver
            ON mt.sender.member_id = m.member_id
        WHERE
            ST_CONTAINS(ST_BUFFER(self.location, meter), m.location)
            AND
            -- self 의 member_type 이 FAMILY 가 아닌 경우
            mt.sender.member_id = self.member_id
            -- self 의 member_type 이 FAMILY 인 경우
            mt.receiver.member_id = self.member_id
            AND
            m.name LIKE '%query%'
            AND
            m.isVerified
            AND
            m.isVisible
        GROUP BY
            m.member_id
        */
        self = jpaQueryFactory.selectFrom(member)
                .from(member)
                .where(member.eq(self))
                .fetchOne();

        QMember qMember1, qMember2;

        if(self.getMemberType() != MemberType.FAMILY){
            qMember1 = meeting.receiver;
            qMember2 = meeting.sender;
        } else {
            qMember1 = meeting.sender;
            qMember2 = meeting.receiver;
        }

        BooleanTemplate isNearby = Expressions.booleanTemplate("ST_CONTAINS(ST_BUFFER({0}, {1}), {2})",
                self.getAddress().getLocation(), meter, member.address.location);

        NumberExpression<Integer> withTime = Expressions.numberTemplate(Integer.class,
                        "SUM(TIMESTAMPDIFF(HOUR, {0}, {1}))", meeting.startTime, meeting.endTime)
                .as("withTime");

        NumberExpression<Double> distance = Expressions.numberTemplate(Double.class,
                        "ST_DISTANCE_SPHERE({0}, {1})", member.address.location, self.getAddress().getLocation())
                .as("distance");

        return jpaQueryFactory.select(Projections.fields(ResMemberMapDTO.class,
                        member.memberId,
                        member.memberType,
                        member.name,
                        member.skill,
                        withTime,
                        distance,
                        member.address.latitude.as("lat"),
                        member.address.longitude.as("lng")))
                .from(member)
                .leftJoin(meeting).on(qMember1.eq(member))
                .where(isNearby
                        .and(member.isVerified)
                        .and(member.isVisible)
                        .and(qMember2.eq(self).or(qMember2.isNull()))
                        .and(member.name.contains(query))
                        .and(member.ne(self)))
                .groupBy(member)
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
        if (my.getMemberType().equals(MemberType.FAMILY)) {
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
                                    withTime.as("withTime")))
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
                                    withTime.as("withTime")))
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

        resMembersRecommendedDTOS = resMembersRecommendedDTOS.stream()
                .peek(resMembersRecommendedDTO -> resMembersRecommendedDTO.setDistance(
                        UnitTrans.meterToKilometer(resMembersRecommendedDTO.getDistance()))).toList();

        assert Objects.nonNull(total);
        return new PageImpl<>(resMembersRecommendedDTOS, pageable, total);
    }

    @Override
    public ResMemberPublicInfoDTO getMemberPublicInfo(Long opponentId, Long selfId) {
        ResMemberPublicInfoDTO resMemberPublicInfoDTO;
        Integer withTime;

        Member selfMember = jpaQueryFactory.select(Projections.fields(Member.class,
                        Projections.fields(Address.class,
                                        member.address.location)
                                .as("address"),
                        member.memberType))
                .from(member)
                .where(member.memberId.eq(selfId))
                .fetchOne();

        NumberTemplate<Double> distance = Expressions.numberTemplate(Double.class,
                "ST_DISTANCE_SPHERE({0}, {1})",
                selfMember.getAddress().getLocation(), member.address.location);

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
                                distance.as("distance"),
                                Projections.fields(Address.class,
                                        member.address.province,
                                        member.address.city,
                                        member.address.district,
                                        member.address.details,
                                        member.address.latitude,
                                        member.address.longitude).as("address"),
                                member.skill))
                .from(member)
                .where(member.memberId.eq(opponentId))
                .fetchOne();

        QMember me = selfMember.getMemberType() == MemberType.FAMILY ? meeting.receiver : meeting.sender;
        QMember oppo = selfMember.getMemberType() == MemberType.FAMILY ? meeting.sender : meeting.receiver;

        withTime = jpaQueryFactory.select(Expressions.numberTemplate(Integer.class,
                        "SUM(TIMESTAMPDIFF(MINUTE, {0}, {1}))",
                        meeting.startTime, meeting.endTime))
                .from(meeting)
                .where(oppo.memberId.eq(opponentId)
                        .and(me.memberId.eq(selfId))
                        .and(meeting.status.eq(MeetingStatus.FINISH)))
                .fetchOne();

        resMemberPublicInfoDTO.setWithTime(Objects.requireNonNullElse(withTime, 0)); // 함께한 시간이 없는 경우 0으로 return
        resMemberPublicInfoDTO.setDistance(UnitTrans.meterToKilometer(resMemberPublicInfoDTO.getDistance()));
        resMemberPublicInfoDTO.setAge(Period.between(resMemberPublicInfoDTO.getBirth(), LocalDate.now()).getYears());

        return resMemberPublicInfoDTO;
    }

    @Override
    public ResMemberPrivateInfoDTO getMemberPrivateInfo(Long memberId) {
        ResMemberPrivateInfoDTO dto = jpaQueryFactory.select(Projections.fields(ResMemberPrivateInfoDTO.class,
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
                        member.address,
                        member.skill))
                .from(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();

        Integer withTimeSum = jpaQueryFactory.select(Expressions.numberTemplate(Integer.class,
                                "SUM(TIMESTAMPDIFF(MINUTE, {0}, {1}))",
                                meeting.startTime, meeting.endTime)
                        .as("withTimeSum"))
                .from(meeting)
                .where(meeting.sender.memberId.eq(memberId))
                .groupBy(meeting.sender)
                .fetchOne();

        dto.setWithTimeSum(Objects.requireNonNullElse(withTimeSum, 0));

        return dto;
    }

}
