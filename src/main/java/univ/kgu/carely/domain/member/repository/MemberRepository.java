package univ.kgu.carely.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {
    Boolean existsByUsername(String username);
    Boolean existsByPhoneNumber(String phoneNumber);
}
