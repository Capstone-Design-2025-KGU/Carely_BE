package univ.kgu.carely.domain.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import univ.kgu.carely.domain.chat.dto.ChatMessageRequest;
import univ.kgu.carely.domain.chat.dto.ChatMessageResponse;
import univ.kgu.carely.domain.chat.entity.ChatMessage;
import univ.kgu.carely.domain.chat.service.ChatMessageService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatWebSocketController {

    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate massagingTemplate;

    /**
     * chat.sendMessage에 메시지를 전송하면 sendMessage 메서드가 실행됩니다.
     * 메서드에서 반환된 값이 구독 중인 클라이언트에게 브로드캐스됩니다. (/topic/public을 구독하는 클라이언트)
     * 메세지 전송시 데이터베이스에 저장합니다.
     * @param request
     * @return ChatMessageResponse
     */
    @MessageMapping("/chat.sendMessage")
    @Operation(summary = "메세지 전송", description = "클라이언트가 메세지를 전송하고, 해당 데이터를 저장합니다.")
    public void sendMessage(@Payload ChatMessageRequest request) {
        log.info("[WS] 메시지 수신! 채팅방: {}, 내용: {}", request.getChatroomId(), request.getContent());
        ChatMessage chatMessage = chatMessageService.saveChatMessage(request);
        ChatMessageResponse response = ChatMessageResponse.from(chatMessage);

        String destination = "/topic/chatroom/" + request.getChatroomId();
        log.info("[WS] {}로 브로드캐스트 시작", destination);
        massagingTemplate.convertAndSend(destination, response);
    }

    /**
     * 클라이언트와 웹소켓의 연결을 Establish 합니다.
     * @param chatMessage
     * @param headerAccessor
     * @return
     */
    @MessageMapping("/chat.addMember")
    @SendTo("/topic/public")
    public ChatMessage addMember(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("member", chatMessage.getSender());
        return chatMessage;
    }
}
