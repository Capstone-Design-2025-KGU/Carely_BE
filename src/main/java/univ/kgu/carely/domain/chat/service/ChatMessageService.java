package univ.kgu.carely.domain.chat.service;

import univ.kgu.carely.domain.chat.dto.ChatMessageRequest;
import univ.kgu.carely.domain.chat.entity.ChatMessage;

public interface ChatMessageService {
    ChatMessage saveChatMessage(ChatMessageRequest request);
}
