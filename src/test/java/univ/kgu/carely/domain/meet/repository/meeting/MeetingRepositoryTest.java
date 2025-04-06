package univ.kgu.carely.domain.meet.repository.meeting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@SpringBootTest
class MeetingRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MeetingRepository meetingRepository;

    @Test
    @DisplayName("")
    void test1() {
        Member me1 = memberRepository.getReferenceById(1L);
        Member me2 = memberRepository.getReferenceById(2L);
        meetingRepository.existsBySenderAndReceiverAndMeetingStatusIsAccept(me1, me2);
    }

}