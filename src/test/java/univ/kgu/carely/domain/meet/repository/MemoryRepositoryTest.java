package univ.kgu.carely.domain.meet.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.meet.repository.memo.MemoRepository;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemoryRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void test1() {
        Member member = memberRepository.getReferenceById(1L);
        memoRepository.findCurrentMemoByMemberAndMeetingStatusFinish(member);
    }
}