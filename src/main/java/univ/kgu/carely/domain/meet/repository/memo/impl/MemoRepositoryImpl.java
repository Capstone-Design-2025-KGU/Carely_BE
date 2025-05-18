package univ.kgu.carely.domain.meet.repository.memo.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import univ.kgu.carely.domain.meet.entity.Memo;
import univ.kgu.carely.domain.meet.entity.QMemo;
import univ.kgu.carely.domain.meet.repository.memo.CustomMemoRepository;
import univ.kgu.carely.domain.member.entity.Member;

@RequiredArgsConstructor
public class MemoRepositoryImpl implements CustomMemoRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QMemo memo = QMemo.memo;

    @Override
    public Memo findCurrentMemoByMember(Member member) {
        return jpaQueryFactory.selectFrom(memo)
                .where(memo.member.eq(member))
                .fetchFirst();
    }

}
