package univ.kgu.carely.domain.chat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import univ.kgu.carely.domain.member.entity.Member;

//ChatMessage_ChatRoom 을 연결하는 중간 테이블

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;
}
