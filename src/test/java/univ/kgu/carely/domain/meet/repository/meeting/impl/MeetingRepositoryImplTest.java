package univ.kgu.carely.domain.meet.repository.meeting.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import univ.kgu.carely.domain.meet.repository.meeting.MeetingRepository;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@SpringBootTest
class MeetingRepositoryImplTest {

    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void findNearestMeetingBySenderOrReceiver() {
        meetingRepository.findNearestMeetingBySenderOrReceiver(memberRepository.getReferenceById(1L));
    }
}