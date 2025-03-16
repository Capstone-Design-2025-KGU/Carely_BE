package univ.kgu.carely.domain.member.entity;

// import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
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

    @Test
    @DisplayName("범위 내에 해당하는 유저 검색")
    void test2(){
        // 경기대 좌표
        List<Member> allWithin = memberRepository.findAllWithin(BigDecimal.valueOf(37.301387),
                BigDecimal.valueOf(127.036554), 100);

        assertThat(allWithin.get(0).getAddress().getDetails()).isEqualTo("경기대 도서관");
    }
}