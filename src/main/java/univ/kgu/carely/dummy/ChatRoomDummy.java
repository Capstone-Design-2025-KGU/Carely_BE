package univ.kgu.carely.dummy;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import univ.kgu.carely.domain.chat.entity.ChatRoom;
import univ.kgu.carely.domain.chat.repository.ChatRoomRepository;

@Configuration
@RequiredArgsConstructor
public class ChatRoomDummy {

    private final ChatRoomRepository chatRoomRepository;

    public void makeChatRoom() {
        ChatRoom oneToOneRoom1 = new ChatRoom(null, new ArrayList<>(), new ArrayList<>(), "1:1 채팅방 1");
        ChatRoom oneToOneRoom2 = new ChatRoom(null, new ArrayList<>(), new ArrayList<>(), "1:1 채팅방 2");
        ChatRoom oneToOneRoom3 = new ChatRoom(null, new ArrayList<>(), new ArrayList<>(), "1:1 채팅방 3");

        ChatRoom groupRoom1 = new ChatRoom(null, new ArrayList<>(), new ArrayList<>(), "그룹 채팅방 1");
        ChatRoom groupRoom2 = new ChatRoom(null, new ArrayList<>(), new ArrayList<>(), "그룹 채팅방 1");
        ChatRoom groupRoom3 = new ChatRoom(null, new ArrayList<>(), new ArrayList<>(), "그룹 채팅방 1");

        chatRoomRepository.saveAll(
                List.of(oneToOneRoom1, oneToOneRoom2, oneToOneRoom3, groupRoom1, groupRoom2, groupRoom3));
    }
}
