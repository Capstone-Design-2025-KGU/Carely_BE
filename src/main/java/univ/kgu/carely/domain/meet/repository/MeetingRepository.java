package univ.kgu.carely.domain.meet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.meet.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
