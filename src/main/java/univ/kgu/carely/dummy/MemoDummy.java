package univ.kgu.carely.dummy;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import univ.kgu.carely.domain.meet.entity.Memo;
import univ.kgu.carely.domain.meet.repository.memo.MemoRepository;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Configuration
@RequiredArgsConstructor
public class MemoDummy {

    private final MemberRepository memberRepository;
    private final MemoRepository memoRepository;

    public void makeMemo() {
        Member flutter = memberRepository.getReferenceById(1L);

        Memo memo = Memo.builder()
                .member(flutter)
                .comm("할아버지는 대화할때 가장 기분이 좋아보이셨다.")
                .medic("투약은 하루 2번, 아침 10시와 저녁 6시에 진행합니다. 또한, 복약 후에는 환자 상태를 세심하게 관찰하여 이상 반응이 없는지 확인해야 합니다.")
                .meal("점심 식사는 30분동안 하셨습니다.")
                .health("건강 상태는 이상 없음.")
                .toilet("화장실을 하루동안 3번 가셨습니다.")
                .walk("산책하는 것을 좋아하십니다.")
                .build();

        memoRepository.save(memo);
    }

}
