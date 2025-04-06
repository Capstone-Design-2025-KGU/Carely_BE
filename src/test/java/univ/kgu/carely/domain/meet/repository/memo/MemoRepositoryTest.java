package univ.kgu.carely.domain.meet.repository.memo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@SpringBootTest
class MemoRepositoryTest {
    @Autowired
    MemoRepository memoRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("a")
    void test1() {
        Member referenceById = memberRepository.getReferenceById(1L);

        memoRepository.findCurrentMemoByMemberAndMeetingStatusFinish(referenceById);
    }
}