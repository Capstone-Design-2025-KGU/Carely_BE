package univ.kgu.carely.domain.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import univ.kgu.carely.domain.chat.entity.ChatMessage;
import univ.kgu.carely.domain.member.entity.Member;

@Controller
public class ChatController {

    /**
     * chat.sendMessage에 메시지를 전송하면 sendMessage 메서드가 실행됩니다.
     * 메서드에서 반환된 값이 구독 중인 클라이언트에게 브로드캐스됩니다. (/topic/public을 구독하는 클라이언트)
     * @param chatMessage
     * @return
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
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

        // 웹 소켓 세션에 유저 이름을 저장합니다.
        // TODO -- username을 가져와야 하는데 서비스 계층에 해당 메서드가 없음. 해당 메서드 개발 후 수정
        headerAccessor.getSessionAttributes().put("member_id", chatMessage.getSender_id());
        return chatMessage;
    }
}
