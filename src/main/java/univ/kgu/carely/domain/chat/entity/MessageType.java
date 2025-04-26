package univ.kgu.carely.domain.chat.entity;

public enum MessageType {

    // 유저가 일반 메세지를 보냈음을 의미합니다.
    CHAT,

    // 유저가 채팅방에 입장했음을 의미합니다.
    JOIN,

    // 유저가 채팅방을 나갔음을 의미합니다.
    LEAVE,

    // 유저가 약속을 요청했음을 의미합니다.
    MEETING_REQUEST,

    // 유저가 약속을 수락했음을 의미합니다.
    MEETING_ACCEPT
}

