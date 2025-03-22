package univ.kgu.carely.domain.chat.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.chat.entity.MessageType;

@Getter
@Setter
@NoArgsConstructor
@Schema(title = "클라이언트 메세지 전송 DTO", description = "클라이언트의 메세지 전송을 위한 DTO 입니다.")
public class ChatMessageRequest {

    @Schema(description = "보낸 멤버 Id", example = "1")
    private Long senderId;

    @Schema(description = "채팅방 Id", example = "909")
    private Long chatroomId;

    @Schema(description = "메세지 내용", example = "안녕하세요?")
    private String content;

    @Schema(description = "메세지 타입", example = "CHAT")
    private MessageType messageType;
}
