package univ.kgu.carely.domain.chat.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import univ.kgu.carely.config.QslConfig;
import univ.kgu.carely.domain.chat.entity.ChatRoom;
import univ.kgu.carely.domain.chat.repository.ChatRoomRepository;

@DataJpaTest
@Import(QslConfig.class)
class ChatRoomRepositoryImplTest {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Test
    void findByOneToOneChatRoom() {
        Optional<ChatRoom> byOneToOneChatRoom = chatRoomRepository.findByOneToOneChatRoom(1L, 3L);

        ChatRoom actual = byOneToOneChatRoom.get();
        assertThat(actual).isNotNull();
        System.out.println(actual.getId());
    }

    @Test
    void findByOneToOneChatRoom2() {
        Optional<ChatRoom> byOneToOneChatRoom = chatRoomRepository.findByOneToOneChatRoom(1L, 4L);

        ChatRoom actual = byOneToOneChatRoom.get();
        assertThat(actual).isNotNull()
                .extracting("id")
                .asString().isEqualTo("2");
        System.out.println(actual.getId());
    }

    @Test
    void findByOneToOneChatRoom1() {
        Optional<ChatRoom> byOneToOneChatRoom = chatRoomRepository.findByOneToOneChatRoom(1L, 2L);

        assertThat(byOneToOneChatRoom).isEmpty();
    }
}