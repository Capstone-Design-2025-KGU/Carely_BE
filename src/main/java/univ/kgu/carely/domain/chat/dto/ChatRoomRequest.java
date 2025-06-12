package univ.kgu.carely.domain.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomRequest {
    private Long senderId;
    private Long receiverId;
}
