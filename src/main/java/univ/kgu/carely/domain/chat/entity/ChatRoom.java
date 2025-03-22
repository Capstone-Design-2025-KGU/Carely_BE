package univ.kgu.carely.domain.chat.entity;

import jakarta.persistence.*;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    private String roomName;
}
