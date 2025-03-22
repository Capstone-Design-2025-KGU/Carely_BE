package univ.kgu.carely.domain.chat.service;

import univ.kgu.carely.domain.chat.entity.ChatMessage;

public interface ChatMessageService {
    void saveChatMessage(ChatMessage chatMessage);
}
