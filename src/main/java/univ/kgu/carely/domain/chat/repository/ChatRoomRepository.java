package univ.kgu.carely.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import univ.kgu.carely.domain.chat.entity.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findByMembers_Member_MemberIdAndMembers_Member_MemberId(Long memberId1, Long memberId2);
}

