package univ.kgu.carely.domain.chat.repository.impl;

import static univ.kgu.carely.domain.chat.entity.QChatMember.chatMember;
import static univ.kgu.carely.domain.chat.entity.QChatRoom.chatRoom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import univ.kgu.carely.domain.chat.entity.ChatRoom;
import univ.kgu.carely.domain.chat.repository.CustomChatRoomRepository;

@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements CustomChatRoomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<ChatRoom> findByOneToOneChatRoom(Long memberId1, Long memberId2) {
        return Optional.ofNullable(jpaQueryFactory.select(chatRoom)
                .from(chatRoom)
                .leftJoin(chatMember).on(chatMember.chatRoom.eq(chatRoom))
                .where(chatMember.member.memberId.in(memberId1, memberId2))
                .groupBy(chatRoom)
                .having(chatMember.countDistinct().eq(2L))
                .fetchFirst());
    }

}
