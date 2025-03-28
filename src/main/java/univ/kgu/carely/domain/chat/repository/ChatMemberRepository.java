package univ.kgu.carely.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.kgu.carely.domain.chat.entity.ChatMember;
import univ.kgu.carely.domain.chat.entity.ChatMessage;
import univ.kgu.carely.domain.chat.entity.ChatRoom;

import java.util.List;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {

    /**
     * 멤버 아이디를 통해 해당 멤버가 속한 모든 채팅방을 조회합니다.
     * @param chatRoomId
     * @return List<ChatMember>
     */
    List<ChatMember> findByMemberId(Long chatRoomId);

    /**
     * 특정한 채팅방에 속한 모든 멤버를 조회합니다.
     * @param chatRoom
     * @return List<ChatMember>
     */
    List<ChatMember> findByChatRoom(ChatRoom chatRoom);
}
