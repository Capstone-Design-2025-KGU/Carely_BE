package univ.kgu.carely.dummy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.meet.entity.MeetingStatus;
import univ.kgu.carely.domain.meet.repository.meeting.MeetingRepository;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class MeetingDummy {

    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;

    public void makeMeeting() {
        Member park = memberRepository.getReferenceById(1L);
        Member cho = memberRepository.getReferenceById(2L);

        Member member3 = memberRepository.getReferenceById(3L);
        Member member4 = memberRepository.getReferenceById(4L);
        Member member6 = memberRepository.getReferenceById(6L);

        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Meeting meeting1 = Meeting.builder()
                .chore("뭐할까")
                .startTime(now.minusHours(5))
                .endTime(now)
                .sender(member4)
                .receiver(park)
                .status(MeetingStatus.FINISH)
                .build();

        Meeting meeting2 = Meeting.builder()
                .chore("나는 뭐해야됨")
                .startTime(now.minusHours(10))
                .endTime(now.minusHours(5))
                .sender(member3)
                .receiver(park)
                .status(MeetingStatus.FINISH)
                .build();

        Meeting meeting3 = Meeting.builder()
                .chore("뭐할까")
                .startTime(now.minusHours(2).minusDays(1))
                .endTime(now.minusDays(1))
                .sender(member4)
                .receiver(park)
                .status(MeetingStatus.FINISH)
                .build();

        Meeting meeting4 = Meeting.builder()
                .chore("뭐할까")
                .startTime(now.minusHours(2).minusDays(1))
                .endTime(now.minusDays(1))
                .sender(member6)
                .receiver(cho)
                .status(MeetingStatus.FINISH)
                .build();

        Meeting meeting5 = Meeting.builder()
                .chore("뭐할까")
                .startTime(now.plusHours(2))
                .endTime(now.plusHours(5))
                .sender(member3)
                .receiver(cho)
                .status(MeetingStatus.ACCEPT)
                .build();

        Meeting meeting6 = Meeting.builder()
                .chore("뭐할까")
                .startTime(now.plusHours(2).plusDays(1))
                .endTime(now.plusHours(5).plusDays(1))
                .sender(member3)
                .receiver(park)
                .status(MeetingStatus.ACCEPT)
                .build();

        meetingRepository.saveAll(List.of(meeting1, meeting2, meeting3, meeting4, meeting5, meeting6));
    }

}
