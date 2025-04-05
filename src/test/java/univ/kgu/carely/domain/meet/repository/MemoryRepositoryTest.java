package univ.kgu.carely.domain.meet.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemoryRepositoryTest {

    @Autowired
    MemoryRepository memoryRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("w?")
    void test1() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Member member = memberRepository.getReferenceById(1L);

        Page<ResMemoryDTO> pagedMemoryByNameContaining = memoryRepository.findPagedMemoryByNameContaining("", member,
                pageRequest);

    }
}