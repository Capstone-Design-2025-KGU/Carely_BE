package univ.kgu.carely.domain.meet.repository.meeting.impl;

import static univ.kgu.carely.domain.meet.entity.QMemo.memo;
import static univ.kgu.carely.domain.member.entity.QMember.member;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingSmallInfoDTO;
import univ.kgu.carely.domain.meet.entity.MeetingStatus;
import univ.kgu.carely.domain.meet.entity.QMeeting;
import univ.kgu.carely.domain.meet.repository.meeting.CustomMeetingRepository;
import univ.kgu.carely.domain.member.dto.response.ResMemberSmallInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.entity.QMember;

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
    public ResMeetingSmallInfoDTO findNearestMeetingBySenderOrReceiver(Member self) {
        MemberType memberType = jpaQueryFactory.select(member.memberType)
                .from(member)
                .where(member.eq(self))
                .fetchOne();

        QMember me = memberType == MemberType.FAMILY ? meeting.receiver : meeting.sender;
        QMember receiver = meeting.receiver;
        QMember sender = meeting.sender;

        return jpaQueryFactory.select(Projections.fields(ResMeetingSmallInfoDTO.class,
                        meeting.id.as("meetingId"),
                        Projections.fields(ResMemberSmallInfoDTO.class,
                                receiver.memberId,
                                receiver.username,
                                receiver.name,
                                receiver.memberType,
                                receiver.profileImage).as("receiver"),
                        Projections.fields(ResMemberSmallInfoDTO.class,
                                sender.memberId,
                                sender.username,
                                sender.name,
                                sender.memberType,
                                sender.profileImage).as("sender"),
                        meeting.startTime,
                        memo.id.as("memoId"),
                        memo.walk,
                        memo.health,
                        memo.medic,
                        memo.comm,
                        memo.meal,
                        memo.toilet))
                .from(meeting)
                .leftJoin(meeting.receiver.memos, memo)
                .where(me.eq(self)
                        .and(meeting.status.eq(MeetingStatus.ACCEPT))
                        .and(meeting.startTime.after(LocalDateTime.now())))
                .orderBy(meeting.startTime.asc())
                .fetchFirst();
    }

}
