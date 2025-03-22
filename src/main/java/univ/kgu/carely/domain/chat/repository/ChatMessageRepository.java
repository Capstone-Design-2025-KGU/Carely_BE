package univ.kgu.carely.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.chat.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
