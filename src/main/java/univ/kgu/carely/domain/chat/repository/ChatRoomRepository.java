package univ.kgu.carely.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.chat.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, CustomChatRoomRepository{
}

