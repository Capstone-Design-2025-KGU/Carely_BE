package univ.kgu.carely.domain.chat.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    private Long senderId;

    private String sender;

    private Long chatroomId;

    private String content;

    private MessageType messageType;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
