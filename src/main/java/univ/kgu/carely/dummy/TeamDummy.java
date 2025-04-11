package univ.kgu.carely.dummy;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import univ.kgu.carely.domain.common.embeded.Address;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.repository.team.TeamRepository;

@Configuration
@RequiredArgsConstructor
@Profile("default")
public class TeamDummy {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public void makeTeam() {
        List<Member> members = memberRepository.findAll();

        Address theSecondEngineeringMuseum = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("경기대학교 수원캠퍼스 제 2공학관")
                .latitude(BigDecimal.valueOf(37.3002807))
                .longitude(BigDecimal.valueOf(127.0399276))
                .build();

        Team theSecondEngineeringMuseumTeam = Team.builder()
                .teamName("경기대 제 2공학관")
                .address(theSecondEngineeringMuseum)
                .build();

        theSecondEngineeringMuseumTeam = teamRepository.save(theSecondEngineeringMuseumTeam);

        Address theNine = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("경기대학교 수원캠퍼스 9강의동 호연관")
                .latitude(BigDecimal.valueOf(37.3042111))
                .longitude(BigDecimal.valueOf(127.0339176))
                .build();

        Team theNineTeam = Team.builder()
                .teamName("경기대 9강의동 호연관")
                .address(theNine)
                .build();

        theNineTeam = teamRepository.save(theNineTeam);
    }
}
