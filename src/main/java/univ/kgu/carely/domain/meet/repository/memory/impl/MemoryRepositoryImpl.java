package univ.kgu.carely.domain.meet.repository.memory.impl;

import static univ.kgu.carely.domain.member.entity.QMember.member;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryCardDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryDTO;
import univ.kgu.carely.domain.meet.entity.QMemory;
import univ.kgu.carely.domain.meet.repository.memory.CustomMemoryRepository;
import univ.kgu.carely.domain.member.dto.response.ResMemberSmallInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.entity.QMember;

@RequiredArgsConstructor
public class MemoryRepositoryImpl implements CustomMemoryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QMemory memory = QMemory.memory;

    @Override
    public Page<ResMemoryDTO> findPagedMemoryByNameContaining(String query, Member me, Pageable pageable) {
        QMember sender = new QMember("sd");
        QMember receiver = new QMember("rc");

        BooleanExpression condition = memory.sender.eq(me)
                .or(memory.receiver.eq(me))
                .and(memory.sender.name.contains(query)
                        .or(memory.receiver.name.contains(query)));
        List<ResMemoryDTO> fetch = jpaQueryFactory.select(Projections.fields(ResMemoryDTO.class,
                        memory.id.as("memoryId"),
                        memory.senderMemo,
                        memory.receiverMemo,
                        memory.createdAt,
                        memory.updatedAt,
                        Projections.fields(ResMemberSmallInfoDTO.class,
                                sender.memberId,
                                sender.username,
                                sender.name,
                                sender.memberType,
                                sender.profileImage).as("sender"),
                        Projections.fields(ResMemberSmallInfoDTO.class,
                                receiver.memberId,
                                receiver.username,
                                receiver.name,
                                receiver.memberType,
                                receiver.profileImage).as("receiver"),
                        memory.meeting.id.as("meetingId")))
                .from(memory)
                .leftJoin(memory.sender, sender)
                .leftJoin(memory.receiver, receiver)
                .where(condition)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(memory.id.desc())
                .fetch();

        Long l = jpaQueryFactory.select(memory.count())
                .from(memory)
                .leftJoin(memory.sender, sender)
                .leftJoin(memory.receiver, receiver)
                .where(condition)
                .fetchOne();

        return new PageImpl<>(fetch, pageable, l.longValue());
    }

    @Override
    public List<ResMemoryCardDTO> findMemoryCardByMember(int count, Member target) {
        MemberType memberType = jpaQueryFactory.select(member.memberType)
                .from(member)
                .where(member.eq(target))
                .fetchOne();

        QMember tar = memberType == MemberType.FAMILY ? memory.receiver : memory.sender;
        QMember other = memberType == MemberType.FAMILY ? memory.sender : memory.receiver;

        StringPath memo = memberType == MemberType.FAMILY ? memory.senderMemo : memory.receiverMemo;

        return jpaQueryFactory.select(Projections.fields(ResMemoryCardDTO.class,
                        memory.id.as("memoryId"),
                        other.memberId.as("otherMemberId"),
                        other.memberType,
                        other.name.as("oppoName"),
                        other.profileImage,
                        memory.createdAt,
                        memo.as("oppoMemo")))
                .from(memory)
                .where(tar.eq(target))
                .orderBy(memory.id.desc())
                .limit(count)
                .fetch();
    }

}
