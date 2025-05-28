package univ.kgu.carely.dummy;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import univ.kgu.carely.domain.common.embeded.address.Address;
import univ.kgu.carely.domain.team.entity.Team;
import univ.kgu.carely.domain.team.repository.team.TeamRepository;

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class TeamDummy {

    private final TeamRepository teamRepository;

    public void makeTeam() {
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);

        Address theSecondEngineeringMuseum = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("경기대학교 수원캠퍼스 제 2공학관")
                .latitude(BigDecimal.valueOf(37.3002807))
                .longitude(BigDecimal.valueOf(127.0399276))
                .location(gf.createPoint(new Coordinate(127.0399276,37.3002807)))
                .build();

        Team theSecondEngineeringMuseumTeam = Team.builder()
                .teamName("경기대 제 2공학관")
                .address(theSecondEngineeringMuseum)
                .build();

        teamRepository.save(theSecondEngineeringMuseumTeam);

        Address theNine = Address.builder()
                .province("경기도")
                .city("수원시")
                .district("영통구")
                .details("경기대학교 수원캠퍼스 9강의동 호연관")
                .latitude(BigDecimal.valueOf(37.3042111))
                .longitude(BigDecimal.valueOf(127.0339176))
                .location(gf.createPoint(new Coordinate(127.0339176, 37.3042111)))
                .build();

        Team theNineTeam = Team.builder()
                .teamName("경기대 9강의동 호연관")
                .address(theNine)
                .build();

        teamRepository.save(theNineTeam);
    }
}
