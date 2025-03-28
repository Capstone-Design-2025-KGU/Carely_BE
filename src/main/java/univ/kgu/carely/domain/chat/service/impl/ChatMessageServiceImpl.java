package univ.kgu.carely.domain.chat.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import univ.kgu.carely.domain.chat.dto.ChatMessageRequest;
import univ.kgu.carely.domain.chat.dto.ChatMessageResponse;
import univ.kgu.carely.domain.chat.dto.ChatRoomResponse;
import univ.kgu.carely.domain.chat.entity.ChatMember;
import univ.kgu.carely.domain.chat.entity.ChatMessage;
import univ.kgu.carely.domain.chat.entity.ChatRoom;
import univ.kgu.carely.domain.chat.repository.ChatMemberRepository;
import univ.kgu.carely.domain.chat.repository.ChatMessageRepository;
import univ.kgu.carely.domain.chat.repository.ChatRoomRepository;
import univ.kgu.carely.domain.chat.service.ChatMessageService;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMemberRepository chatMemberRepository;

    /**
     * 채팅 메세지를 데이터베이스에 저장합니다.
     * 보낸 멤버의 아이디와 채팅방의 아이디를 찾아서 검증합니다.
     * @param request
     * @return ChatMessage
     */
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

    /**
     * 채팅방 Id를 통해 메세지 내역을 불러옵니다.
     * @param chatRoomId
     * @return List<ChatMessageResponse>
     */
    @Override
    public List<ChatMessageResponse> getChatMessageByChatRoomId(Long chatRoomId) {
        List<ChatMessage> messages = chatMessageRepository.findByChatRoomIdOrderByCreatedAtAsc(chatRoomId);

        return messages.stream().map(ChatMessageResponse::from).collect(Collectors.toList());
    }

    @Override
    public List<ChatRoomResponse> getChatRoomByMemberId(Long memberId) {
        // 멤버가 속한 모든 ChatMember를 가져옵니다.
        List<ChatMember> myChatMembers = chatMemberRepository.findByMemberId(memberId);

        // 가져온 ChatMember에서 ChatRoom을 사용합니다.
        return myChatMembers.stream()
                .map(ChatMember::getChatRoom)
                .distinct()
                .map(chatRoom -> {
                    // 최신 메시지를 가져옵니다.
                    List<ChatMessage> messages = chatMessageRepository.findByChatRoomIdOrderByCreatedAtAsc(chatRoom.getId());
                    ChatMessage latest = messages.isEmpty() ? null : messages.get(messages.size() - 1);

                    List<ChatMember> participants = chatMemberRepository.findByChatRoom(chatRoom);
                    Member opponent = participants.stream()
                            .map(ChatMember::getMember)
                            .filter(m -> !m.getMemberId().equals(memberId))
                            .findFirst()
                            .orElse(null);

                    if (opponent == null) {
                        log.warn("[채팅방:{}] 멤버:{}의 상대방을 찾을 수 없습니다", chatRoom.getId(), memberId);
                        return null;
                    }

                    int participantCount = participants.size();

                    return new ChatRoomResponse(
                            opponent.getMemberId(),
                            opponent.getName(),
                            opponent.getMemberType(),
                            opponent.getProfileImage(),
                            chatRoom.getId(),
                            latest != null ? latest.getContent() : "아직 메시지가 없어요",
                            participantCount,
                            latest != null ? latest.getCreatedAt() : null
                    );
                })
                .filter(room -> room != null)
                .collect(Collectors.toList());
    }
}
