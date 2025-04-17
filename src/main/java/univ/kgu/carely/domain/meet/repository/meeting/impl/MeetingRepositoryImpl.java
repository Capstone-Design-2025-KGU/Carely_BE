package univ.kgu.carely.domain.meet.repository.meeting.impl;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import univ.kgu.carely.domain.meet.entity.MeetingStatus;
import univ.kgu.carely.domain.meet.entity.QMeeting;
import univ.kgu.carely.domain.meet.repository.meeting.CustomMeetingRepository;
import univ.kgu.carely.domain.member.entity.Member;

@RequiredArgsConstructor
public class MeetingRepositoryImpl implements CustomMeetingRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QMeeting meeting = QMeeting.meeting;

    @Override
    public Boolean existsBySenderAndReceiverAndMeetingStatusIsAccept(Member sender, Member receiver) {
        return null != jpaQueryFactory.selectOne()
                .from(meeting)
                .where(meeting.sender.eq(sender)
                        .and(meeting.receiver.eq(receiver))
                        .and(meeting.status.eq(MeetingStatus.ACCEPT)))
                .fetchOne();
    }

    @Override
    public Integer sumAllWithTime(Long memberId) {
        return jpaQueryFactory.select(Expressions.numberTemplate(Integer.class,
                        "SUM(TIMESTAMPDIFF(MINUTE, {0}, {1}))",
                        meeting.startTime, meeting.endTime))
                .from(meeting)
                .where(meeting.sender.memberId.eq(memberId)
                        .and(meeting.status.eq(MeetingStatus.FINISH)))
                .fetchOne();
    }

    @Override
    public Integer sumOpponentWithTime(Long senderId, Long receiverId) {
        return jpaQueryFactory.select(Expressions.numberTemplate(Integer.class,
                "SUM(TIMESTAMPDIFF(MINUTE, {0}, {1}))",
                meeting.startTime, meeting.endTime))
                .from(meeting)
                .where(meeting.sender.memberId.eq(senderId)
                        .and(meeting.receiver.memberId.eq(receiverId)))
                .fetchOne();
    }

}
