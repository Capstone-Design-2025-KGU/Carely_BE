package univ.kgu.carely.domain.meet.repository.meeting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import univ.kgu.carely.config.QslConfig;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(QslConfig.class)
class MeetingRepositoryTest {

    @Autowired
    MeetingRepository meetingRepository;

    @Test
    void test1() {
        Integer i = meetingRepository.sumAllWithTime(4L);
    }

    @Test
    void test2() {
        meetingRepository.sumOpponentWithTime(1L, 2L);
    }
}