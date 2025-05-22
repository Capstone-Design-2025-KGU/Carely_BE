package univ.kgu.carely.domain.meet.repository.memory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("View Of Receiver")
    void test1() {
        Member flutter = memberRepository.getReferenceById(1L);

        List<ResMemoryCardDTO> cardByMember = memoryRepository.findMemoryCardByMember(5, flutter);

        assertEquals("ㅂㅈㄷㄱ", cardByMember.get(0).getMemo());
        assertEquals("회원2", cardByMember.get(0).getOppoName());
        assertEquals("ㅁㄴㅇㄹ", cardByMember.get(1).getMemo());
        assertEquals("회원3", cardByMember.get(1).getOppoName());
        assertThrows(IndexOutOfBoundsException.class, () -> cardByMember.get(3));
    }

    @Test
    @DisplayName("View Of Sender")
    void test2() {
        Member member3 = memberRepository.getReferenceById(3L);

        List<ResMemoryCardDTO> cardByMember = memoryRepository.findMemoryCardByMember(5, member3);

        assertThat(cardByMember.get(0).getOppoName()).isEqualTo("박성민");
        assertThat(cardByMember.get(0).getMemo()).contains("고생");
    }

}