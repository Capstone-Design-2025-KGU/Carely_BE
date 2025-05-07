package univ.kgu.carely.domain.chat.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import univ.kgu.carely.domain.member.entity.Member;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private Long meetingId;

    private String date;

    private String time;

    private String chore;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
