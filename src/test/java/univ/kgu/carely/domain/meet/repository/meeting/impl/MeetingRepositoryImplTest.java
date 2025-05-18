package univ.kgu.carely.domain.meet.repository.meeting.impl;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingSmallInfoDTO;
import univ.kgu.carely.domain.meet.repository.meeting.MeetingRepository;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@SpringBootTest
class MeetingRepositoryImplTest {

    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void findNearestMeetingByReceiver() {
        ResMeetingSmallInfoDTO nearestMeetingBySenderOrReceiver = meetingRepository.findNearestMeetingBySenderOrReceiver(
                memberRepository.getReferenceById(1L));

        assertThat(nearestMeetingBySenderOrReceiver.getMeetingId()).isEqualTo(6);
    }

    @Test
    void findNearestMeetingBySender() {
        ResMeetingSmallInfoDTO nearestMeetingBySenderOrReceiver = meetingRepository.findNearestMeetingBySenderOrReceiver(
                memberRepository.getReferenceById(3L));

        assertThat(nearestMeetingBySenderOrReceiver.getMeetingId()).isEqualTo(5);
    }

}