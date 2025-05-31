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
public class MemberDummy {

    private final BCryptPasswordEncoder encoder;
    private final MemberRepository memberRepository;

    public void makeMember() {
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);

        Address[] addresses = new Address[]{
                Address.builder().province("서울").city("강남구").district("영동대로").details("123").latitude(BigDecimal.valueOf(37.300627)).longitude(BigDecimal.valueOf(127.037393)).location(gf.createPoint(new Coordinate(127.037393, 37.300627))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("경기대 8강의동").latitude(BigDecimal.valueOf(37.300781)).longitude(BigDecimal.valueOf(127.039357)).location(gf.createPoint(new Coordinate(127.039357, 37.300781))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("경기대 기숙사").latitude(BigDecimal.valueOf(37.297700)).longitude(BigDecimal.valueOf(127.038627)).location(gf.createPoint(new Coordinate(127.038627, 37.297700))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("수원 외국어 고등학교").latitude(BigDecimal.valueOf(37.295942)).longitude(BigDecimal.valueOf(127.035106)).location(gf.createPoint(new Coordinate(127.035106, 37.295942))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("경기대 도서관").latitude(BigDecimal.valueOf(37.301131)).longitude(BigDecimal.valueOf(127.035591)).location(gf.createPoint(new Coordinate(127.035591, 37.301131))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("경기대 2강의동").latitude(BigDecimal.valueOf(37.299603)).longitude(BigDecimal.valueOf(127.033605)).location(gf.createPoint(new Coordinate(127.033605, 37.299603))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("수원 역사 박물관").latitude(BigDecimal.valueOf(37.297632)).longitude(BigDecimal.valueOf(127.035301)).location(gf.createPoint(new Coordinate(127.035301, 37.297632))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("창용초등학교").latitude(BigDecimal.valueOf(37.299185)).longitude(BigDecimal.valueOf(127.031417)).location(gf.createPoint(new Coordinate(127.031417, 37.299185))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("경기대 9강의동").latitude(BigDecimal.valueOf(37.304126)).longitude(BigDecimal.valueOf(127.033916)).location(gf.createPoint(new Coordinate(127.033916, 37.304126))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("광교역").latitude(BigDecimal.valueOf(37.302104)).longitude(BigDecimal.valueOf(127.044228)).location(gf.createPoint(new Coordinate(127.044228, 37.302104))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("광교 홍재 도서관").latitude(BigDecimal.valueOf(37.302906)).longitude(BigDecimal.valueOf(127.047461)).location(gf.createPoint(new Coordinate(127.047461, 37.302906))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("광교 중학교").latitude(BigDecimal.valueOf(37.304502)).longitude(BigDecimal.valueOf(127.044605)).location(gf.createPoint(new Coordinate(127.044605, 37.304502))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("보훈 재활 체육센터").latitude(BigDecimal.valueOf(37.297572)).longitude(BigDecimal.valueOf(127.024646)).location(gf.createPoint(new Coordinate(127.024646, 37.297572))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("수원 보훈지청").latitude(BigDecimal.valueOf(37.295925)).longitude(BigDecimal.valueOf(127.022575)).location(gf.createPoint(new Coordinate(127.022575, 37.295925))).build(),
                Address.builder().province("경기도").city("수원시").district("영통구").details("광교 공원").latitude(BigDecimal.valueOf(37.301006)).longitude(BigDecimal.valueOf(127.030117)).location(gf.createPoint(new Coordinate(127.030117, 37.301006))).build()
        };

        Skill[] skills = new Skill[]{
                Skill.builder().communication(SkillLevel.HIGH).meal(SkillLevel.MIDDLE).toilet(SkillLevel.LOW).bath(SkillLevel.HIGH).walk(SkillLevel.MIDDLE).build(),
                Skill.builder().communication(SkillLevel.MIDDLE).meal(SkillLevel.HIGH).toilet(SkillLevel.MIDDLE).bath(SkillLevel.LOW).walk(SkillLevel.HIGH).build(),
                Skill.builder().communication(SkillLevel.LOW).meal(SkillLevel.MIDDLE).toilet(SkillLevel.HIGH).bath(SkillLevel.MIDDLE).walk(SkillLevel.LOW).build(),
                Skill.builder().communication(SkillLevel.HIGH).meal(SkillLevel.LOW).toilet(SkillLevel.HIGH).bath(SkillLevel.LOW).walk(SkillLevel.HIGH).build(),
                Skill.builder().communication(SkillLevel.MIDDLE).meal(SkillLevel.MIDDLE).toilet(SkillLevel.MIDDLE).bath(SkillLevel.MIDDLE).walk(SkillLevel.MIDDLE).build(),
                Skill.builder().communication(SkillLevel.LOW).meal(SkillLevel.LOW).toilet(SkillLevel.LOW).bath(SkillLevel.LOW).walk(SkillLevel.LOW).build(),
                Skill.builder().communication(SkillLevel.HIGH).meal(SkillLevel.HIGH).toilet(SkillLevel.HIGH).bath(SkillLevel.HIGH).walk(SkillLevel.HIGH).build(),
                Skill.builder().communication(SkillLevel.MIDDLE).meal(SkillLevel.LOW).toilet(SkillLevel.MIDDLE).bath(SkillLevel.HIGH).walk(SkillLevel.LOW).build(),
                Skill.builder().communication(SkillLevel.LOW).meal(SkillLevel.HIGH).toilet(SkillLevel.LOW).bath(SkillLevel.MIDDLE).walk(SkillLevel.HIGH).build(),
                Skill.builder().communication(SkillLevel.HIGH).meal(SkillLevel.MIDDLE).toilet(SkillLevel.HIGH).bath(SkillLevel.MIDDLE).walk(SkillLevel.LOW).build(),
                Skill.builder().communication(SkillLevel.MIDDLE).meal(SkillLevel.HIGH).toilet(SkillLevel.LOW).bath(SkillLevel.HIGH).walk(SkillLevel.MIDDLE).build(),
                Skill.builder().communication(SkillLevel.LOW).meal(SkillLevel.MIDDLE).toilet(SkillLevel.MIDDLE).bath(SkillLevel.LOW).walk(SkillLevel.HIGH).build(),
                Skill.builder().communication(SkillLevel.HIGH).meal(SkillLevel.LOW).toilet(SkillLevel.MIDDLE).bath(SkillLevel.HIGH).walk(SkillLevel.LOW).build(),
                Skill.builder().communication(SkillLevel.MIDDLE).meal(SkillLevel.HIGH).toilet(SkillLevel.HIGH).bath(SkillLevel.LOW).walk(SkillLevel.MIDDLE).build(),
                Skill.builder().communication(SkillLevel.LOW).meal(SkillLevel.LOW).toilet(SkillLevel.HIGH).bath(SkillLevel.MIDDLE).walk(SkillLevel.HIGH).build()
        };

        String[] names = {"박성민", "조건희", "박철민", "우종억", "김장훈", "윤하성", "장태수", "이정훈", "최은주", "황지민", "백승훈", "김예진", "조아라", "신동하", "정유진", "임건우"};
        String[] usernames = {"flutter", "user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9", "user10", "user11", "user12", "user13", "user14", "user15"};
        MemberType[] types = {MemberType.FAMILY, MemberType.FAMILY, MemberType.VOLUNTEER, MemberType.CAREGIVER, MemberType.FAMILY, MemberType.VOLUNTEER, MemberType.CAREGIVER, MemberType.FAMILY, MemberType.VOLUNTEER, MemberType.CAREGIVER, MemberType.FAMILY, MemberType.VOLUNTEER, MemberType.CAREGIVER, MemberType.FAMILY, MemberType.VOLUNTEER, MemberType.CAREGIVER};

        List<Member> members = new java.util.ArrayList<>();
        for (int i = 0; i < usernames.length; i++) {
            members.add(Member.builder()
                    .username(usernames[i])
                    .password(encoder.encode("pass" + i))
                    .name(names[i])
                    .phoneNumber("010-1234-56" + String.format("%02d", i))
                    .birth(LocalDate.of(2001, 10, 30))
                    .story("안녕하세요, 저는 회원 " + i + "입니다.")
                    .memberType(types[i])
                    .isVisible(true)
                    .isVerified(true)
                    .address(addresses[i % addresses.length])
                    .skill(skills[i % skills.length])
                    .build());
        }

        memberRepository.saveAll(members);
    }
}
