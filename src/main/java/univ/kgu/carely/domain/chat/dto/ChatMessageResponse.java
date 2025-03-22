package univ.kgu.carely.domain.chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import univ.kgu.carely.domain.chat.entity.MessageType;

import java.time.LocalDateTime;

public class ChatMessageResponse {

    @Schema(description = "보낸 멤버 Id", example = "1")
    private Long senderId;

    @Schema(description = "채팅방 Id", example = "909")
    private Long chatroomId;

    @Schema(description = "메시지 내용", example = "안녕하세요?")
    private String content;

    @Schema(description = "메시지 타입", example = "CHAT")
    private MessageType messageType;

    @Schema(description = "메시지 생성 시간", example = "2025-03-21T17:45:34.658")
    private LocalDateTime timestamp;
}
