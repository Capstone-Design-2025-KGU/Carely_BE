package univ.kgu.carely.domain.meet.repository.meeting.impl;

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

}
