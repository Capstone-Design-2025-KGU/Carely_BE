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
                .sender(member4)  // 예: 자원봉사자
                .receiver(flutter)  // 예: 가족간병인
                .senderMemo("짧은 시간이었지만 함께 어르신을 돌보며 많은 걸 배웠어요. 덕분에 큰 보람을 느낄 수 있었습니다. 늘 힘내세요!")
                .receiverMemo("자원봉사자님, 따뜻한 마음으로 도와주셔서 정말 감사했어요. 덕분에 저도 한결 힘이 났습니다. 다음에 또 뵐 수 있길 바랍니다.")
                .build();

        Memory memory2 = Memory.builder()
                .meeting(meetingRepository.getReferenceById(2L))
                .sender(member3)  // 예: 예비요양보호사
                .receiver(flutter)  // 예: 가족간병인
                .senderMemo("첫 현장이었는데 많이 긴장했지만 옆에서 챙겨주셔서 큰 도움이 되었어요. 앞으로 더 열심히 배우겠습니다!")
                .receiverMemo("예비요양보호사님, 처음이라 힘드셨을텐데 잘 따라와주셔서 고마웠어요. 앞으로도 좋은 요양보호사가 되실 거라 믿습니다.")
                .build();

        memoryRepository.saveAll(List.of(memory1, memory2));
    }

}
