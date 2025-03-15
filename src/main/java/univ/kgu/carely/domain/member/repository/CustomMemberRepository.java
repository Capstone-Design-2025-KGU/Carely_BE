package univ.kgu.carely.domain.member.repository;

import univ.kgu.carely.domain.member.entity.Member;

public interface CustomMemberRepository {
    Member findByName(String name);
}
