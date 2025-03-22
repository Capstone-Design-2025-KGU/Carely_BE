package univ.kgu.carely.domain.member.entity;

// import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.map.dto.request.ReqViewPortInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
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

        assertThat(member.getMemberId()).isEqualTo(1);
    }

    @Test
    @DisplayName("범위 내에 해당하는 유저 검색")
    void test2(){
        ReqViewPortInfoDTO viewPortInfoDTO = new ReqViewPortInfoDTO();
        viewPortInfoDTO.setLbLat(BigDecimal.valueOf(37.2869235));
        viewPortInfoDTO.setLbLng(BigDecimal.valueOf(127.0190130));
        viewPortInfoDTO.setRtLat(BigDecimal.valueOf(37.3119958));
        viewPortInfoDTO.setRtLng(BigDecimal.valueOf(127.0578250));
        // 경기대 좌표
        List<ResMemberPublicInfoDTO> allWithin = memberRepository.findAllWithinDistance(BigDecimal.valueOf(37.301387),
                BigDecimal.valueOf(127.036554), 2000);

        assertThat(allWithin).hasSize(15);
    }
}