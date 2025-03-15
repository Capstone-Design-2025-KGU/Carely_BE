package univ.kgu.carely.domain.member.entity;

// import static org.junit.jupiter.api.Assertions.*;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.kgu.carely.domain.common.embeded.Address;
import univ.kgu.carely.domain.common.embeded.Skill;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.common.enums.SkillLevel;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Transactional
@SpringBootTest
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 추가 확인")
    public void test1(){
        Address address = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("장안구")
                .latitude(BigDecimal.valueOf(37.2871000))
                .longitude(BigDecimal.valueOf(127.0290000))
                .build();

        Skill skill = Skill.builder()
                .communication(SkillLevel.HIGH)
                .meal(SkillLevel.MIDDLE)
                .toilet(SkillLevel.LOW)
                .bath(SkillLevel.HIGH)
                .walk(SkillLevel.LOW)
                .build();

        Member member = Member.builder()
                .username("hello1234")
                .password("1234")
                .name("chogunhee")
                .phoneNumber("010-1234-5678")
                .birth(LocalDate.of(2001,10,30))
                .story("안녕하세요")
                .memberType(MemberType.FAMILY)
                .isVisible(true)
                .isVerified(true)
                .profileImage(null)
                .address(address)
                .skill(skill)
                .build();

        memberRepository.save(member);
    }
}