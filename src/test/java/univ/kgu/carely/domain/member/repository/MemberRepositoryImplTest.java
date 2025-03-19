package univ.kgu.carely.domain.member.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import univ.kgu.carely.domain.common.embeded.Address;

@SpringBootTest
@ActiveProfiles("test")
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("주소 파악 테스트")
    void findAddressByMemberId() {
        Address address = memberRepository.findAddressByMemberId(1L);

        assertThat(address.getCity()).isEqualTo("수원시");
    }
}