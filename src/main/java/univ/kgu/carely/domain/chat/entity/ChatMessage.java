package univ.kgu.carely.domain.chat.entity;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private Long sender_id;

    private String sender;

    private Long chatroom_id;

    private String content;

    private MessageType messageType;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
