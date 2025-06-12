package univ.kgu.carely.domain.chat.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomRequest {
    private Long senderId;
    private Long receiverId;
}
