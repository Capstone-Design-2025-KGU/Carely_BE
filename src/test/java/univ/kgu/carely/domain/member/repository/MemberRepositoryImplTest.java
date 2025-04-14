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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import univ.kgu.carely.config.QslConfig;
import univ.kgu.carely.domain.member.entity.Member;

//@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(QslConfig.class)
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findAllWithinDistance() {
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);

        memberRepository.findAllWithinDistance("", gf.createPoint(new Coordinate(127.039357,37.300781)), 2000);

    }

    @Test
    void findRecommendedMembers() {
        Member member = memberRepository.findById(1L).orElseThrow();

        PageRequest pageRequest = PageRequest.of(0, 10);

        memberRepository.findRecommendedMembers(2000, member, pageRequest);
    }
}