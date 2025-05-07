package univ.kgu.carely.dummy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import univ.kgu.carely.domain.common.embeded.address.Address;
import univ.kgu.carely.domain.common.embeded.skill.Skill;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.common.enums.SkillLevel;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Configuration
@RequiredArgsConstructor
@Profile("default")
public class MemberDummy {

    private final BCryptPasswordEncoder encoder;
    private final MemberRepository memberRepository;

    public void makeMember() {
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);

        Address address1 = Address.builder()
                .province("서울")
                .city("강남구")
                .district("영동대로")
                .details("123")
                .latitude(BigDecimal.valueOf(37.300627))
                .longitude(BigDecimal.valueOf(127.037393))
                .location(gf.createPoint(new Coordinate(127.037393,37.300627)))
                .build();

        Skill skill1 = Skill.builder()
                .communication(SkillLevel.HIGH)
                .meal(SkillLevel.MIDDLE)
                .toilet(SkillLevel.LOW)
                .bath(SkillLevel.HIGH)
                .walk(SkillLevel.MIDDLE)
                .build();

        Member tester = Member.builder()
                .username("flutter")
                .password(encoder.encode("1234"))
                .name("박성민")
                .phoneNumber("010-4321-5678")
                .birth(LocalDate.of(2001, 10, 30))
                .story("저는 테스터 계정입니다.")
                .memberType(MemberType.FAMILY)
                .isVisible(true)
                .isVerified(true)
                .address(address1)
                .skill(skill1)
                .build();

        Member member1 = Member.builder()
                .username("user1")
                .password(encoder.encode("pass1"))
                .name("chogunhee")
                .phoneNumber("010-1234-5671")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 1입니다.")
                .memberType(MemberType.FAMILY)
                .isVisible(true)
                .isVerified(true)
                .address(address1)
                .skill(skill1)
                .build();

        Address address2 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("경기대 8강의동")
                .latitude(BigDecimal.valueOf(37.300781))
                .longitude(BigDecimal.valueOf(127.039357))
                .location(gf.createPoint(new Coordinate(127.039357, 37.300781)))
                .build();

        Skill skill2 = Skill.builder()
                .communication(SkillLevel.MIDDLE)
                .meal(SkillLevel.HIGH)
                .toilet(SkillLevel.MIDDLE)
                .bath(SkillLevel.LOW)
                .walk(SkillLevel.HIGH)
                .build();

        Member member2 = Member.builder()
                .username("user2")
                .password(encoder.encode("pass2"))
                .name("회원2")
                .phoneNumber("010-1234-5672")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 2입니다.")
                .memberType(MemberType.VOLUNTEER)
                .isVisible(true)
                .isVerified(true)
                .address(address2)
                .skill(skill2)
                .build();

        Address address3 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("경기대 기숙사")
                .latitude(BigDecimal.valueOf(37.297700))
                .longitude(BigDecimal.valueOf(127.038627))
                .location(gf.createPoint(new Coordinate(127.038627,37.297700)))
                .build();

        Skill skill3 = Skill.builder()
                .communication(SkillLevel.LOW)
                .meal(SkillLevel.MIDDLE)
                .toilet(SkillLevel.HIGH)
                .bath(SkillLevel.MIDDLE)
                .walk(SkillLevel.LOW)
                .build();

        Member member3 = Member.builder()
                .username("user3")
                .password(encoder.encode("pass3"))
                .name("회원3")
                .phoneNumber("010-1234-5673")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 3입니다.")
                .memberType(MemberType.CAREGIVER)
                .isVisible(true)
                .isVerified(true)
                .address(address3)
                .skill(skill3)
                .build();

        Address address4 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("수원 외국어 고등학교")
                .latitude(BigDecimal.valueOf(37.295942))
                .longitude(BigDecimal.valueOf(127.035106))
                .location(gf.createPoint(new Coordinate(127.035106,37.295942)))
                .build();

        Skill skill4 = Skill.builder()
                .communication(SkillLevel.HIGH)
                .meal(SkillLevel.HIGH)
                .toilet(SkillLevel.LOW)
                .bath(SkillLevel.HIGH)
                .walk(SkillLevel.MIDDLE)
                .build();

        Member member4 = Member.builder()
                .username("user4")
                .password(encoder.encode("pass4"))
                .name("회원4")
                .phoneNumber("010-1234-5674")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 4입니다.")
                .memberType(MemberType.FAMILY)
                .isVisible(true)
                .isVerified(true)
                .address(address4)
                .skill(skill4)
                .build();

        Address address5 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("경기대 도서관")
                .latitude(BigDecimal.valueOf(37.301131))
                .longitude(BigDecimal.valueOf(127.035591))
                .location(gf.createPoint(new Coordinate(127.035591,37.301131)))
                .build();

        Skill skill5 = Skill.builder()
                .communication(SkillLevel.MIDDLE)
                .meal(SkillLevel.LOW)
                .toilet(SkillLevel.HIGH)
                .bath(SkillLevel.LOW)
                .walk(SkillLevel.HIGH)
                .build();

        Member member5 = Member.builder()
                .username("user5")
                .password(encoder.encode("pass5"))
                .name("회원5")
                .phoneNumber("010-1234-5675")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 5입니다.")
                .memberType(MemberType.VOLUNTEER)
                .isVisible(true)
                .isVerified(true)
                .address(address5)
                .skill(skill5)
                .build();

        Address address6 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("경기대 2강의동")
                .latitude(BigDecimal.valueOf(37.299603))
                .longitude(BigDecimal.valueOf(127.033605))
                .location(gf.createPoint(new Coordinate(127.033605,37.299603)))
                .build();

        Skill skill6 = Skill.builder()
                .communication(SkillLevel.HIGH)
                .meal(SkillLevel.MIDDLE)
                .toilet(SkillLevel.LOW)
                .bath(SkillLevel.HIGH)
                .walk(SkillLevel.MIDDLE)
                .build();

        Member member6 = Member.builder()
                .username("user6")
                .password(encoder.encode("pass6"))
                .name("회원6")
                .phoneNumber("010-1234-5676")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 6입니다.")
                .memberType(MemberType.CAREGIVER)
                .isVisible(true)
                .isVerified(true)
                .address(address6)
                .skill(skill6)
                .build();

        Address address7 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("수원 역사 박물관")
                .latitude(BigDecimal.valueOf(37.297632))
                .longitude(BigDecimal.valueOf(127.035301))
                .location(gf.createPoint(new Coordinate(127.035301,37.297632)))
                .build();

        Skill skill7 = Skill.builder()
                .communication(SkillLevel.MIDDLE)
                .meal(SkillLevel.HIGH)
                .toilet(SkillLevel.MIDDLE)
                .bath(SkillLevel.LOW)
                .walk(SkillLevel.HIGH)
                .build();

        Member member7 = Member.builder()
                .username("user7")
                .password(encoder.encode("pass7"))
                .name("회원7")
                .phoneNumber("010-1234-5677")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 7입니다.")
                .memberType(MemberType.FAMILY)
                .isVisible(true)
                .isVerified(true)
                .address(address7)
                .skill(skill7)
                .build();

        Address address8 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("창용초등학교")
                .latitude(BigDecimal.valueOf(37.299185))
                .longitude(BigDecimal.valueOf(127.031417))
                .location(gf.createPoint(new Coordinate(127.031417,37.299185)))
                .build();

        Skill skill8 = Skill.builder()
                .communication(SkillLevel.LOW)
                .meal(SkillLevel.MIDDLE)
                .toilet(SkillLevel.HIGH)
                .bath(SkillLevel.MIDDLE)
                .walk(SkillLevel.LOW)
                .build();

        Member member8 = Member.builder()
                .username("user8")
                .password(encoder.encode("pass8"))
                .name("회원8")
                .phoneNumber("010-1234-5678")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 8입니다.")
                .memberType(MemberType.VOLUNTEER)
                .isVisible(true)
                .isVerified(true)
                .address(address8)
                .skill(skill8)
                .build();

        Address address9 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("경기대 9강의동")
                .latitude(BigDecimal.valueOf(37.304126))
                .longitude(BigDecimal.valueOf(127.033916))
                .location(gf.createPoint(new Coordinate(127.033916,37.304126)))
                .build();

        Skill skill9 = Skill.builder()
                .communication(SkillLevel.HIGH)
                .meal(SkillLevel.HIGH)
                .toilet(SkillLevel.LOW)
                .bath(SkillLevel.HIGH)
                .walk(SkillLevel.MIDDLE)
                .build();

        Member member9 = Member.builder()
                .username("user9")
                .password(encoder.encode("pass9"))
                .name("회원9")
                .phoneNumber("010-1234-5679")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 9입니다.")
                .memberType(MemberType.CAREGIVER)
                .isVisible(true)
                .isVerified(true)
                .address(address9)
                .skill(skill9)
                .build();

        Address address10 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("광교역")
                .latitude(BigDecimal.valueOf(37.302104))
                .longitude(BigDecimal.valueOf(127.044228))
                .location(gf.createPoint(new Coordinate(127.044228,37.302104)))
                .build();

        Skill skill10 = Skill.builder()
                .communication(SkillLevel.MIDDLE)
                .meal(SkillLevel.LOW)
                .toilet(SkillLevel.HIGH)
                .bath(SkillLevel.LOW)
                .walk(SkillLevel.HIGH)
                .build();

        Member member10 = Member.builder()
                .username("user10")
                .password(encoder.encode("pass10"))
                .name("회원10")
                .phoneNumber("010-1234-5680")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 10입니다.")
                .memberType(MemberType.FAMILY)
                .isVisible(true)
                .isVerified(true)
                .address(address10)
                .skill(skill10)
                .build();

        Address address11 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("광교 홍재 도서관")
                .latitude(BigDecimal.valueOf(37.302906))
                .longitude(BigDecimal.valueOf(127.047461))
                .location(gf.createPoint(new Coordinate(127.047461,37.302906)))
                .build();

        Skill skill11 = Skill.builder()
                .communication(SkillLevel.HIGH)
                .meal(SkillLevel.MIDDLE)
                .toilet(SkillLevel.LOW)
                .bath(SkillLevel.HIGH)
                .walk(SkillLevel.MIDDLE)
                .build();

        Member member11 = Member.builder()
                .username("user11")
                .password(encoder.encode("pass11"))
                .name("회원11")
                .phoneNumber("010-1234-5681")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 11입니다.")
                .memberType(MemberType.VOLUNTEER)
                .isVisible(true)
                .isVerified(true)
                .address(address11)
                .skill(skill11)
                .build();

        Address address12 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("광교 중학교")
                .latitude(BigDecimal.valueOf(37.304502))
                .longitude(BigDecimal.valueOf(127.044605))
                .location(gf.createPoint(new Coordinate(127.044605,37.304502)))
                .build();

        Skill skill12 = Skill.builder()
                .communication(SkillLevel.MIDDLE)
                .meal(SkillLevel.HIGH)
                .toilet(SkillLevel.MIDDLE)
                .bath(SkillLevel.LOW)
                .walk(SkillLevel.HIGH)
                .build();

        Member member12 = Member.builder()
                .username("user12")
                .password(encoder.encode("pass12"))
                .name("회원12")
                .phoneNumber("010-1234-5682")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 12입니다.")
                .memberType(MemberType.CAREGIVER)
                .isVisible(true)
                .isVerified(true)
                .address(address12)
                .skill(skill12)
                .build();

        Address address13 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("보훈 재활 체육센터")
                .latitude(BigDecimal.valueOf(37.297572))
                .longitude(BigDecimal.valueOf(127.024646))
                .location(gf.createPoint(new Coordinate(127.024646,37.297572)))
                .build();

        Skill skill13 = Skill.builder()
                .communication(SkillLevel.LOW)
                .meal(SkillLevel.MIDDLE)
                .toilet(SkillLevel.HIGH)
                .bath(SkillLevel.MIDDLE)
                .walk(SkillLevel.LOW)
                .build();

        Member member13 = Member.builder()
                .username("user13")
                .password(encoder.encode("pass13"))
                .name("회원13")
                .phoneNumber("010-1234-5683")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 13입니다.")
                .memberType(MemberType.FAMILY)
                .isVisible(true)
                .isVerified(true)
                .address(address13)
                .skill(skill13)
                .build();

        Address address14 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("수원 보훈지청")
                .latitude(BigDecimal.valueOf(37.295925))
                .longitude(BigDecimal.valueOf(127.022575))
                .location(gf.createPoint(new Coordinate(127.022575,37.295925)))
                .build();

        Skill skill14 = Skill.builder()
                .communication(SkillLevel.HIGH)
                .meal(SkillLevel.HIGH)
                .toilet(SkillLevel.LOW)
                .bath(SkillLevel.HIGH)
                .walk(SkillLevel.MIDDLE)
                .build();

        Member member14 = Member.builder()
                .username("user14")
                .password(encoder.encode("pass14"))
                .name("회원14")
                .phoneNumber("010-1234-5684")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 14입니다.")
                .memberType(MemberType.VOLUNTEER)
                .isVisible(true)
                .isVerified(true)
                .address(address14)
                .skill(skill14)
                .build();

        Address address15 = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("광교 공원")
                .latitude(BigDecimal.valueOf(37.301006))
                .longitude(BigDecimal.valueOf(127.030117))
                .location(gf.createPoint(new Coordinate(127.030117,37.301006)))
                .build();

        Skill skill15 = Skill.builder()
                .communication(SkillLevel.MIDDLE)
                .meal(SkillLevel.LOW)
                .toilet(SkillLevel.HIGH)
                .bath(SkillLevel.LOW)
                .walk(SkillLevel.HIGH)
                .build();

        Member member15 = Member.builder()
                .username("user15")
                .password(encoder.encode("pass15"))
                .name("회원15")
                .phoneNumber("010-1234-5685")
                .birth(LocalDate.of(2001, 10, 30))
                .story("안녕하세요, 저는 회원 15입니다.")
                .memberType(MemberType.CAREGIVER)
                .isVisible(true)
                .isVerified(true)
                .address(address15)
                .skill(skill15)
                .build();

        memberRepository.saveAll(
                List.of(tester, member1, member2, member3, member4, member5, member6, member7, member8, member9,
                        member10, member11, member12, member13, member14, member15));
    }
}
