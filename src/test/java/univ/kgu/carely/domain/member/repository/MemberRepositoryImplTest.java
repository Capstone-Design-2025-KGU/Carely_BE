package univ.kgu.carely.domain.member.repository;

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
import univ.kgu.carely.config.QslConfig;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(QslConfig.class)
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findAllWithinDistance() {
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);

        memberRepository.findAllWithinDistance("", gf.createPoint(new Coordinate(36,127)), 2000);

    }
}