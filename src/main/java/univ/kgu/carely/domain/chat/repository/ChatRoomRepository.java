package univ.kgu.carely.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import univ.kgu.carely.domain.chat.entity.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("""
        SELECT r FROM ChatRoom r
        JOIN ChatMember m1 ON m1.chatRoom = r AND m1.member.memberId = :member1
        JOIN ChatMember m2 ON m2.chatRoom = r AND m2.member.memberId = :member2
    """)
    Optional<ChatRoom> findByMemberIds(@Param("member1") Long member1, @Param("member2") Long member2);
}
