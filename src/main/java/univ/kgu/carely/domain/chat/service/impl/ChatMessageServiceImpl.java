package univ.kgu.carely.domain.chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ.kgu.carely.domain.chat.dto.ChatMessageRequest;
import univ.kgu.carely.domain.chat.entity.ChatMessage;
import univ.kgu.carely.domain.chat.entity.ChatRoom;
import univ.kgu.carely.domain.chat.repository.ChatMessageRepository;
import univ.kgu.carely.domain.chat.repository.ChatRoomRepository;
import univ.kgu.carely.domain.chat.service.ChatMessageService;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatMessage saveChatMessage(ChatMessageRequest request) {
        Member sender = memberRepository.findById(request.getSenderId())
                        .orElseThrow(()-> new RuntimeException(String.format("Id : %d 의 멤버를 찾을 수 없습니다.", request.getSenderId())));

        ChatRoom room = chatRoomRepository.findById(request.getChatroomId())
                .orElseThrow(()-> new RuntimeException(String.format("Id : %d 의 채팅방을 찾을 수 없습니다.", request.getChatroomId())));

        ChatMessage message = ChatMessage.builder()
                        .sender(sender)
                                .chatRoom(room)
                                        .content(request.getContent())
                                                .messageType(request.getMessageType())
                                                        .build();

        return chatMessageRepository.save(message);
    }
}
