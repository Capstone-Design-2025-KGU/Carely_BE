package univ.kgu.carely.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.chat.entity.ChatMember;
import univ.kgu.carely.domain.chat.entity.ChatMessage;
import univ.kgu.carely.domain.chat.entity.ChatRoom;

import java.util.List;

public interface ChatMemberRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMember> findByMember_MemberId(Long chatRoomId);
}
