package univ.kgu.carely.domain.member.repository;

import java.math.BigDecimal;
import java.util.List;
import univ.kgu.carely.domain.member.entity.Member;

public interface CustomMemberRepository {
    Member findByName(String name);

    List<Member> findAllWithin(BigDecimal lat, BigDecimal lng, int meter);
}
