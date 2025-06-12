package univ.kgu.carely.domain.chat.repository;

import java.util.Optional;
import univ.kgu.carely.domain.chat.entity.ChatRoom;

public interface CustomChatRoomRepository {
    Optional<ChatRoom> findByOneToOneChatRoom(Long memberId1, Long memberId2);
}
