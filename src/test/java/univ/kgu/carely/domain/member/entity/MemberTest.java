package univ.kgu.carely.domain.member.entity;

// import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Transactional
@SpringBootTest
@ActiveProfiles({"test"})
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("유저 이름으로 검색")
    void test1(){
        Member member = memberRepository.findByName("chogunhee");

        assertThat(member.getId()).isEqualTo(1);
    }
}