package univ.kgu.carely.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.entity.QMember;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QMember member = QMember.member;

    @Override
    public Member findByName(String name){

        return jpaQueryFactory.select(member)
                .from(member)
                .where(member.name.eq(name))
                .fetchOne();
    }
}
