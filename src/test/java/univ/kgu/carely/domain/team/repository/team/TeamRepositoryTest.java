package univ.kgu.carely.domain.team.repository.team;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import univ.kgu.carely.config.QslConfig;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(QslConfig.class)
class TeamRepositoryTest {

    @Autowired
    TeamRepository teamRepository;

    @Test
    void test1() {
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);

        PageRequest pageRequest = PageRequest.of(0, 10);

        teamRepository.findTeamOutlineWithinDistance(gf.createPoint(new Coordinate(127.039357,37.300781)), 2000, pageRequest);
    }

}