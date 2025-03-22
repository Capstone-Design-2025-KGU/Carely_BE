package univ.kgu.carely.config.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import univ.kgu.carely.domain.chat.entity.ChatMessage;
import univ.kgu.carely.domain.chat.entity.MessageType;
import univ.kgu.carely.domain.member.entity.Member;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * 사용자가 연결을 끊을 때(WebSocket Disconnected) 해당 상황을 다른 클라이언트에게 브로드캐스트 합니다.
     * WebSocket의 속성에서 유저 이름을 가져온 뒤 연결 해제 메세지를 전송합니다.
     * @param event
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Member member = (Member)headerAccessor.getSessionAttributes().get("member");
        if (member != null) {
            log.info("User Disconnected: {}", member.getName());
            var chatMessage = ChatMessage.builder()
                    .messageType(MessageType.LEAVE)
                    .sender(member)
                    .build();

            // 특정 위치로 메세지를 전송합니다. (해당 위치는 모든 클라이언트가 구독 중)
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
