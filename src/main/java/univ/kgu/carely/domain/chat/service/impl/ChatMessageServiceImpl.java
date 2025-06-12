package univ.kgu.carely.domain.chat.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import univ.kgu.carely.domain.chat.dto.ChatMessageRequest;
import univ.kgu.carely.domain.chat.dto.ChatMessageResponse;
import univ.kgu.carely.domain.chat.dto.ChatRoomRequest;
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
import java.util.Optional;
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
                .meetingId(request.getMeetingId())
                .date(request.getDate())
                .time(request.getTime())
                .chore(request.getChore())
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
        List<ChatMember> myChatMembers = chatMemberRepository.findByMember_MemberId(memberId);

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


                    return ChatRoomResponse.builder()
                            .memberId(opponent.getMemberId())
                            .memberName(opponent.getName())
                            .memberType(opponent.getMemberType())
                            .profileImage(opponent.getProfileImage())
                            .chatRoomId(chatRoom.getId())
                            .content(latest != null ? latest.getContent() : "아직 메시지가 없어요")
                            .participantCount(participantCount)
                            .createdAt(latest != null ? latest.getCreatedAt() : null)
                            .build();
                })
                .filter(room -> room != null)
                .toList();
    }

    @Override
    @Transactional
    public Long createChatRoom(ChatRoomRequest request, Member auth) {
        Long receiverId = request.getReceiverId();

        // 이미 존재하는 채팅방이 있는지 확인
        Optional<ChatRoom> existingRoom = chatRoomRepository.findByOneToOneChatRoom(receiverId,auth.getMemberId());

        if (existingRoom.isPresent()) {
            return existingRoom.get().getId();  // 이미 존재하면 그 채팅방 ID 반환
        }

        Member sender = getMemberOrThrow(auth.getMemberId(), "보내는 사람 없음");
        Member receiver = getMemberOrThrow(receiverId, "받는 사람 없음");

        // 새로운 채팅방 생성
        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.builder()
                .roomName(sender.getName() + " & " + receiver.getName())
                .build());

        // 참여자 등록
        chatMemberRepository.save(ChatMember.builder()
                .chatRoom(chatRoom)
                .member(sender)
                .build());

        chatMemberRepository.save(ChatMember.builder()
                .chatRoom(chatRoom)
                .member(receiver)
                .build());

        return chatRoom.getId();
    }

    private Member getMemberOrThrow(Long id, String errorMessage) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(errorMessage + " (id=" + id + ")"));
    }


    @Override
    public Boolean deleteChatRoom(Long chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
        return true;
    }
}
