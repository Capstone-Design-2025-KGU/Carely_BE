package univ.kgu.carely.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {
    /** 해당 username이 존재하는지 확인한다. */
    Boolean existsByUsername(String username);
    /** 해당 phoneNumber가 존재하는지 확인한다. */
    Boolean existsByPhoneNumber(String phoneNumber);
}
