package univ.kgu.carely.domain.meet.repository.memory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryCardDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@SpringBootTest
class MemoryRepositoryTest {

    @Autowired
    MemoryRepository memoryRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void test1() {
        Member flutter = memberRepository.getReferenceById(1L);

        List<ResMemoryCardDTO> cardByMember = memoryRepository.findMemoryCardByMember(5, flutter);


    }

}