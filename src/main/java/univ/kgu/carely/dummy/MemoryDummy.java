package univ.kgu.carely.dummy;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.meet.entity.Memory;
import univ.kgu.carely.domain.meet.repository.meeting.MeetingRepository;
import univ.kgu.carely.domain.meet.repository.memory.MemoryRepository;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Configuration
@Profile("default")
@RequiredArgsConstructor
public class MemoryDummy {

    private final MemberRepository memberRepository;
    private final MeetingRepository meetingRepository;
    private final MemoryRepository memoryRepository;

    public void makeMemory() {
        Member flutter = memberRepository.getReferenceById(1L);
        Member member3 = memberRepository.getReferenceById(3L);
        Member member4 = memberRepository.getReferenceById(4L);

        Memory memory1 = Memory.builder()
                .meeting(meetingRepository.getReferenceById(1L))
                .sender(member4)
                .receiver(flutter)
                // ToDo : 수정 필요함.
                .senderMemo("ㅁㄴㅇㄹ")
                .receiverMemo("고생많으셨습니다. 다음에 또 봐요!")
                .build();

        Memory memory2 = Memory.builder()
                .meeting(meetingRepository.getReferenceById(2L))
                .sender(member3)
                .receiver(flutter)
                // ToDo : 수정 필요함.
                .senderMemo("ㅂㅈㄷㄱ")
                .receiverMemo("고생하셨어요!")
                .build();

        memoryRepository.saveAll(List.of(memory1, memory2));
    }

}
