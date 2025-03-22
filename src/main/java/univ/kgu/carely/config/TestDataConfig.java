package univ.kgu.carely.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import univ.kgu.carely.domain.chat.entity.ChatMember;
import univ.kgu.carely.domain.chat.entity.ChatRoom;
import univ.kgu.carely.domain.chat.repository.ChatMemberRepository;
import univ.kgu.carely.domain.chat.repository.ChatRoomRepository;
import univ.kgu.carely.domain.common.embeded.Address;
import univ.kgu.carely.domain.common.embeded.Skill;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.common.enums.SkillLevel;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.team.entity.Comment;
import univ.kgu.carely.domain.team.entity.Post;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.entity.TeamMate;
import univ.kgu.carely.domain.team.entity.TeamRole;
import univ.kgu.carely.domain.team.repository.CommentRepository;
import univ.kgu.carely.domain.team.repository.PostRepository;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.entity.TeamMate;
import univ.kgu.carely.domain.team.entity.TeamRole;
import univ.kgu.carely.domain.team.repository.TeamRepository;

@Configuration
@RequiredArgsConstructor
@Profile({"test", "default"})
@Slf4j
public class TestDataConfig {

    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final TeamRepository teamRepository;
    private final BCryptPasswordEncoder encoder;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            log.info("Test Data are Injecting");
// Tester
            Address address1 = Address.builder()
                    .province("서울")
                    .city("강남구")
                    .district("영동대로")
                    .details("123")
                    .latitude(BigDecimal.valueOf(37.300627))
                    .longitude(BigDecimal.valueOf(127.037393))
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
                    .phoneNumber("010-4183-5678")
                    .birth(LocalDate.of(2001, 10, 30))
                    .story("저는 테스터 계정입니다.")
                    .memberType(MemberType.FAMILY)
                    .isVisible(true)
                    .isVerified(true)
                    .address(address1)
                    .skill(skill1)
                    .build();

// Member 1

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

// Member 2
            Address address2 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("경기대 8강의동")
                    .latitude(BigDecimal.valueOf(37.300781))
                    .longitude(BigDecimal.valueOf(127.039357))
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

// Member 3
            Address address3 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("경기대 기숙사")
                    .latitude(BigDecimal.valueOf(37.297700))
                    .longitude(BigDecimal.valueOf(127.038627))
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

// Member 4
            Address address4 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("수원 외국어 고등학교")
                    .latitude(BigDecimal.valueOf(37.295942))
                    .longitude(BigDecimal.valueOf(127.035106))
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

// Member 5
            Address address5 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("경기대 도서관")
                    .latitude(BigDecimal.valueOf(37.301131))
                    .longitude(BigDecimal.valueOf(127.035591))
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

// Member 6
            Address address6 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("경기대 2강의동")
                    .latitude(BigDecimal.valueOf(37.299603))
                    .longitude(BigDecimal.valueOf(127.033605))
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

// Member 7
            Address address7 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("수원 역사 박물관")
                    .latitude(BigDecimal.valueOf(37.297632))
                    .longitude(BigDecimal.valueOf(127.035301))
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

// Member 8
            Address address8 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("창용초등학교")
                    .latitude(BigDecimal.valueOf(37.299185))
                    .longitude(BigDecimal.valueOf(127.031417))
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

// Member 9
            Address address9 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("경기대 9강의동")
                    .latitude(BigDecimal.valueOf(37.304126))
                    .longitude(BigDecimal.valueOf(127.033916))
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

// Member 10
            Address address10 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("광교역")
                    .latitude(BigDecimal.valueOf(37.302104))
                    .longitude(BigDecimal.valueOf(127.044228))
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

// Member 11
            Address address11 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("광교 홍재 도서관")
                    .latitude(BigDecimal.valueOf(37.302906))
                    .longitude(BigDecimal.valueOf(127.047461))
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

// Member 12
            Address address12 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("광교 중학교")
                    .latitude(BigDecimal.valueOf(37.304502))
                    .longitude(BigDecimal.valueOf(127.044605))
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

// Member 13
            Address address13 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("보훈 재활 체육센터")
                    .latitude(BigDecimal.valueOf(37.297572))
                    .longitude(BigDecimal.valueOf(127.024646))
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

// Member 14
            Address address14 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("수원 보훈지청")
                    .latitude(BigDecimal.valueOf(37.295925))
                    .longitude(BigDecimal.valueOf(127.022575))
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

// Member 15
            Address address15 = Address.builder()
                    .province("경기도")
                    .city("수원시")
                    .district("영통구")
                    .details("광교 공원")
                    .latitude(BigDecimal.valueOf(37.301006))
                    .longitude(BigDecimal.valueOf(127.030117))
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

            Member savedTester = memberRepository.save(tester);

            memberRepository.saveAll(
                    List.of(tester, member1, member2, member3, member4, member5, member6, member7, member8, member9,
                            member10,
                            member11, member12, member13, member14, member15));

            // 멤버 다시 불러오기 (DB에서 실제 ID 포함된 멤버 객체로)
            List<Member> savedMembers = memberRepository.findAll();

// 1:1 채팅방 3개
            ChatRoom oneToOneRoom1 = chatRoomRepository.save(ChatRoom.builder().roomName("1:1 채팅방 1").build());
            ChatRoom oneToOneRoom2 = chatRoomRepository.save(ChatRoom.builder().roomName("1:1 채팅방 2").build());
            ChatRoom oneToOneRoom3 = chatRoomRepository.save(ChatRoom.builder().roomName("1:1 채팅방 3").build());

// 그룹 채팅방 3개
            ChatRoom groupRoom1 = chatRoomRepository.save(ChatRoom.builder().roomName("그룹 채팅방 1").build());
            ChatRoom groupRoom2 = chatRoomRepository.save(ChatRoom.builder().roomName("그룹 채팅방 2").build());
            ChatRoom groupRoom3 = chatRoomRepository.save(ChatRoom.builder().roomName("그룹 채팅방 3").build());

// ChatMember 저장
            chatMemberRepository.saveAll(List.of(
                    // 1:1
                    ChatMember.builder().chatRoom(oneToOneRoom1).member(savedTester).build(),
                    ChatMember.builder().chatRoom(oneToOneRoom1).member(savedMembers.get(1)).build(), // user1

                    ChatMember.builder().chatRoom(oneToOneRoom2).member(savedTester).build(),
                    ChatMember.builder().chatRoom(oneToOneRoom2).member(savedMembers.get(2)).build(), // user2

                    ChatMember.builder().chatRoom(oneToOneRoom3).member(savedTester).build(),
                    ChatMember.builder().chatRoom(oneToOneRoom3).member(savedMembers.get(3)).build(), // user3

                    // 그룹 채팅방 (tester + 2명씩)
                    ChatMember.builder().chatRoom(groupRoom1).member(savedTester).build(),
                    ChatMember.builder().chatRoom(groupRoom1).member(savedMembers.get(3)).build(),
                    ChatMember.builder().chatRoom(groupRoom1).member(savedMembers.get(4)).build(),

                    ChatMember.builder().chatRoom(groupRoom2).member(savedTester).build(),
                    ChatMember.builder().chatRoom(groupRoom2).member(savedMembers.get(5)).build(),
                    ChatMember.builder().chatRoom(groupRoom2).member(savedMembers.get(6)).build(),

                    ChatMember.builder().chatRoom(groupRoom3).member(savedTester).build(),
                    ChatMember.builder().chatRoom(groupRoom3).member(savedMembers.get(7)).build(),
                    ChatMember.builder().chatRoom(groupRoom3).member(savedMembers.get(8)).build()
            ));

            Team team = Team.builder()
                    .teamName("경기대 화이팅")
                    .address(address15)
                    .build();

            team = teamRepository.save(team);

            TeamMate teamMate = TeamMate.builder()
                    .team(team)
                    .member(member1)
                    .role(TeamRole.LEADER)
                    .build();

            TeamMate teamMate1 = TeamMate.builder()
                    .team(team)
                    .member(member2)
                    .role(TeamRole.MATE)
                    .build();

            TeamMate teamMate2 = TeamMate.builder()
                    .team(team)
                    .member(member3)
                    .role(TeamRole.MATE)
                    .build();

            team.getTeamMates().addAll(List.of(teamMate, teamMate1, teamMate2));

            teamRepository.save(team);

            Team team1 = Team.builder()
                    .address(address1)
                    .teamName("잉?")
                    .build();

            TeamMate tm1 = TeamMate.builder()
                    .team(team1)
                    .role(TeamRole.LEADER)
                    .member(member5)
                    .build();

            TeamMate tm2 = TeamMate.builder()
                    .team(team)
                    .role(TeamRole.MATE)
                    .member(member6)
                    .build();

            team1.getTeamMates().addAll(List.of(tm1, tm2));

            teamRepository.save(team1);

            Post post = Post.builder()
                    .title("안녕하세욥")
                    .content("반가워용")
                    .team(team)
                    .member(member1)
                    .build();

            post = postRepository.save(post);

            Comment comment = Comment.builder()
                    .post(post)
                    .member(member2)
                    .content("네")
                    .build();

            Comment comment2 = Comment.builder()
                    .post(post)
                    .member(member3)
                    .content("저도 반가워요!")
                    .build();

            post.getComments().addAll(List.of(comment, comment2));
            postRepository.save(post);

            Post post1 = Post.builder()
                    .title("두번째 팀입니다.")
                    .member(member5)
                    .team(team1)
                    .content("강남팸")
                    .build();

            post1 = postRepository.save(post1);

            Comment comment1 = Comment.builder()
                    .member(member6)
                    .post(post1)
                    .content("ㅎㅎㅎㅎㅎ")
                    .build();

            post1.getComments().add(comment1);

            postRepository.save(post1);
        };
    }
}