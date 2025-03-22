package univ.kgu.carely.domain.chat.service;

import univ.kgu.carely.domain.chat.dto.ChatMessageRequest;
import univ.kgu.carely.domain.chat.dto.ChatMessageResponse;
import univ.kgu.carely.domain.chat.entity.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    ChatMessage saveChatMessage(ChatMessageRequest request);
    List<ChatMessageResponse> getChatMessageByChatRoomId(Long chatRoomId);
}
