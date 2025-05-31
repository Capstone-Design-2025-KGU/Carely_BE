package univ.kgu.carely.dummy;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import univ.kgu.carely.domain.chat.entity.ChatMember;
import univ.kgu.carely.domain.chat.entity.ChatRoom;
import univ.kgu.carely.domain.chat.repository.ChatMemberRepository;
import univ.kgu.carely.domain.chat.repository.ChatRoomRepository;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@Configuration
@RequiredArgsConstructor
public class ChatMemberDummy {

    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMemberRepository chatMemberRepository;

    public void makeChatMember() {
        List<Member> members = memberRepository.findAll();
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        ChatMember chatMember = ChatMember.builder()
                .member(members.get(0))
                .chatRoom(chatRooms.get(0))
                .build();

        ChatMember chatMember1 = ChatMember.builder()
                .member(members.get(1))
                .chatRoom(chatRooms.get(0))
                .build();

        ChatMember chatMember2 = ChatMember.builder()
                .member(members.get(0))
                .chatRoom(chatRooms.get(1))
                .build();

        ChatMember chatMember3 = ChatMember.builder()
                .member(members.get(2))
                .chatRoom(chatRooms.get(1))
                .build();

        ChatMember chatMember4 = ChatMember.builder()
                .member(members.get(0))
                .chatRoom(chatRooms.get(2))
                .build();

        ChatMember chatMember5 = ChatMember.builder()
                .member(members.get(3))
                .chatRoom(chatRooms.get(2))
                .build();

        ChatMember chatMember6 = ChatMember.builder()
                .member(members.get(0))
                .chatRoom(chatRooms.get(3))
                .build();

        ChatMember chatMember7 = ChatMember.builder()
                .member(members.get(4))
                .chatRoom(chatRooms.get(3))
                .build();

        ChatMember chatMember8 = ChatMember.builder()
                .member(members.get(5))
                .chatRoom(chatRooms.get(3))
                .build();

        ChatMember chatMember9 = ChatMember.builder()
                .member(members.get(0))
                .chatRoom(chatRooms.get(4))
                .build();

        ChatMember chatMember10 = ChatMember.builder()
                .member(members.get(6))
                .chatRoom(chatRooms.get(4))
                .build();

        ChatMember chatMember11 = ChatMember.builder()
                .member(members.get(7))
                .chatRoom(chatRooms.get(4))
                .build();

        ChatMember chatMember12 = ChatMember.builder()
                .member(members.get(0))
                .chatRoom(chatRooms.get(5))
                .build();

        ChatMember chatMember13 = ChatMember.builder()
                .member(members.get(8))
                .chatRoom(chatRooms.get(5))
                .build();

        ChatMember chatMember14 = ChatMember.builder()
                .member(members.get(9))
                .chatRoom(chatRooms.get(5))
                .build();

        chatMemberRepository.saveAll(
                List.of(chatMember1, chatMember2, chatMember3, chatMember4, chatMember5, chatMember6, chatMember7,
                        chatMember8, chatMember9, chatMember10, chatMember11, chatMember12, chatMember13,
                        chatMember14));
    }
}
