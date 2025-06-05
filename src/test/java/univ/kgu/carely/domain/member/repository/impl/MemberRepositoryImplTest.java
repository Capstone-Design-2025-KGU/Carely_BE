package univ.kgu.carely.domain.member.repository.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.kgu.carely.domain.member.dto.response.ResMemberMapDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findAllWithinDistance() {
        Member member = memberRepository.getReferenceById(1L);
        List<ResMemberMapDTO> allWithinDistance = memberRepository.findAllWithinDistance("", 2000, member);
        allWithinDistance.forEach(System.out::println);
    }

    @Test
    void findAllWithinDistance1() {
        Member member = memberRepository.getReferenceById(3L);
        List<ResMemberMapDTO> allWithinDistance = memberRepository.findAllWithinDistance("", 2000, member);
        allWithinDistance.forEach(System.out::println);
    }
}