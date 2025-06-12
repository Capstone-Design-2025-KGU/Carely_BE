package univ.kgu.carely.domain.chat.service;

import univ.kgu.carely.domain.chat.dto.ChatMessageRequest;
import univ.kgu.carely.domain.chat.dto.ChatMessageResponse;
import univ.kgu.carely.domain.chat.dto.ChatRoomRequest;
import univ.kgu.carely.domain.chat.dto.ChatRoomResponse;
import univ.kgu.carely.domain.chat.entity.ChatMessage;

import java.util.List;
import univ.kgu.carely.domain.member.entity.Member;

public interface ChatMessageService {
    ChatMessage saveChatMessage(ChatMessageRequest request);
    List<ChatMessageResponse> getChatMessageByChatRoomId(Long chatRoomId);
    List<ChatRoomResponse> getChatRoomByMemberId(Long memberId);
    Long createChatRoom(ChatRoomRequest request, Member auth);
    Boolean deleteChatRoom(Long chatRoomId);
}
