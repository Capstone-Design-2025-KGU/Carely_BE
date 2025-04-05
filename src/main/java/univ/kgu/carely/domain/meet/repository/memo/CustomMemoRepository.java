package univ.kgu.carely.domain.meet.repository.memo;

import univ.kgu.carely.domain.meet.entity.Memo;
import univ.kgu.carely.domain.member.entity.Member;

public interface CustomMemoRepository {
    Memo findCurrentMemoByMemberAndMeetingStatusFinish(Member member);
}
