package univ.kgu.carely.domain.member.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import univ.kgu.carely.domain.map.dto.request.ReqCoordinationDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.member.service.MemberService;

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("GPS 기반 이웃 인증 테스트1")
    void verifyNeighbor() {
        // 경기대 5강 좌표
        ReqCoordinationDTO reqCoordinationDTO = new ReqCoordinationDTO();
        reqCoordinationDTO.setLat(BigDecimal.valueOf(37.2999573));
        reqCoordinationDTO.setLng(BigDecimal.valueOf(127.0368483));

        // ID 1은 e스퀘어 좌표 50m 안에는 들지 못함.
        Boolean verified = memberService.verifyNeighbor(reqCoordinationDTO);
        assertThat(verified).isFalse();
    }

    @Test
    @DisplayName("GPS 기반 이웃 인증 테스트1")
    void verifyNeighbor1() {
        // 경기대 4강 좌표
        ReqCoordinationDTO reqCoordinationDTO = new ReqCoordinationDTO();
        reqCoordinationDTO.setLat(BigDecimal.valueOf(37.3005858));
        reqCoordinationDTO.setLng(BigDecimal.valueOf(127.0369585));

        Member member = memberRepository.findById(1L).orElseThrow();
        assertThat(member.getIsVerified()).isTrue();

        // ID 1은 e스퀘어 좌표 4강의 끝부분은 e스퀘어와 가깝기 때문에 인증 가능
        Boolean verified = memberService.verifyNeighbor(reqCoordinationDTO);
        assertThat(verified).isTrue();

        member = memberRepository.findById(1L).orElseThrow();
        assertThat(member.getIsVerified()).isTrue();
    }
}