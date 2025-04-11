package univ.kgu.carely.domain.meet.repository.meeting;

import univ.kgu.carely.domain.member.entity.Member;

public interface CustomMeetingRepository {
    Boolean existsBySenderAndReceiverAndMeetingStatusIsAccept(Member sender, Member receiver);
}
