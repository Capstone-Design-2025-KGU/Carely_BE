-- Member 테이블 생성
CREATE TABLE member (
    member_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL UNIQUE,
    birth DATE NOT NULL,
    story TEXT,
    member_type VARCHAR(50) NOT NULL,
    is_visible BOOLEAN NOT NULL,
    is_verified BOOLEAN NOT NULL DEFAULT FALSE,
    profile_image VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- Address 임베디드 필드
    province VARCHAR(255),
    city VARCHAR(255),
    district VARCHAR(255),
    details VARCHAR(255),
    lat DECIMAL(10,7) NOT NULL,
    lng DECIMAL(10,7) NOT NULL,
    location POINT SRID 4326 NOT NULL,
    -- Skill 임베디드 필드 (SkillLevel ENUM 적용)
    communication ENUM('LOW', 'MIDDLE', 'HIGH'),
    meal ENUM('LOW', 'MIDDLE', 'HIGH'),
    toilet ENUM('LOW', 'MIDDLE', 'HIGH'),
    bath ENUM('LOW', 'MIDDLE', 'HIGH'),
    walk ENUM('LOW', 'MIDDLE', 'HIGH'),
    -- location 필드에 공간 인덱스 추가
    SPATIAL INDEX idx_location (location)
) ENGINE=InnoDB;

-- Team 테이블 생성
CREATE TABLE team (
    team_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(255),
    team_image VARCHAR(255),
    story VARCHAR(255),
    -- Address 임베디드 필드
    province VARCHAR(255),
    city VARCHAR(255),
    district VARCHAR(255),
    details VARCHAR(255),
    lat DECIMAL(10,7) NOT NULL,
    lng DECIMAL(10,7) NOT NULL,
    location POINT SRID 4326 NOT NULL,
    -- location 필드에 공간 인덱스 추가
    SPATIAL INDEX idx_location (location)
) ENGINE=InnoDB;

-- TeamMate 테이블 생성
CREATE TABLE team_mate (
    team_mate_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role ENUM('LEADER', 'MATE') NOT NULL,
    team_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    -- 외래 키 제약
    CONSTRAINT fk_teammate_team FOREIGN KEY (team_id) REFERENCES team(team_id) ON DELETE CASCADE,
    CONSTRAINT fk_teammate_member FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Post 테이블 생성
CREATE TABLE post (
    post_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    member_id BIGINT NOT NULL,
    team_id BIGINT NOT NULL,
    -- 외래 키 제약
    CONSTRAINT fk_post_member FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_post_team FOREIGN KEY (team_id) REFERENCES team(team_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Comment 테이블 생성
CREATE TABLE comment (
    comment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    post_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    -- 외래 키 제약
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE,
    CONSTRAINT fk_comment_member FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ChatRoom 테이블 생성
CREATE TABLE chat_room (
    chatroom_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    room_name VARCHAR(255)
) ENGINE=InnoDB;

-- ChatMember 테이블 생성
CREATE TABLE chat_member (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id BIGINT NOT NULL,
    chatroom_id BIGINT NOT NULL,
    -- 외래 키 제약
    CONSTRAINT fk_chatmember_member FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_chatmember_chatroom FOREIGN KEY (chatroom_id) REFERENCES chat_room(chatroom_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ChatMessage 테이블 생성
CREATE TABLE chat_message (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id BIGINT NOT NULL,
    chatroom_id BIGINT NOT NULL,
    content TEXT,
    message_type ENUM('CHAT', 'JOIN', 'LEAVE', 'MEETING_REQUEST', 'MEETING_ACCEPT') NOT NULL,
    meeting_id BIGINT,
    date VARCHAR(255),
    time VARCHAR(255),
    chore VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -- 외래 키 제약
    CONSTRAINT fk_chatmessage_member FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_chatmessage_chatroom FOREIGN KEY (chatroom_id) REFERENCES chat_room(chatroom_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Meeting 테이블 생성
CREATE TABLE meeting (
    meeting_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    start_time DATETIME,
    end_time DATETIME,
    chore VARCHAR(255),
    status ENUM('PENDING', 'ACCEPT', 'FINISH') DEFAULT 'PENDING',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    -- 외래 키 제약
    CONSTRAINT fk_meeting_sender FOREIGN KEY (sender_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_meeting_receiver FOREIGN KEY (receiver_id) REFERENCES member(member_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Memo 테이블 생성
CREATE TABLE memo (
    memo_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    comm TEXT,
    meal TEXT,
    toilet TEXT,
    walk TEXT,
    medic TEXT,
    health TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    member_id BIGINT NOT NULL,
    -- 외래 키 제약
    CONSTRAINT fk_memo_member FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE,
) ENGINE=InnoDB;

-- Memory 테이블 생성
CREATE TABLE memory (
    memory_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender_memo TEXT DEFAULT '정보없음',
    receiver_memo TEXT DEFAULT '정보없음',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    meeting_id BIGINT NOT NULL,
    -- 외래 키 제약
    CONSTRAINT fk_memory_sender FOREIGN KEY (sender_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_memory_receiver FOREIGN KEY (receiver_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT fk_memory_meeting FOREIGN KEY (meeting_id) REFERENCES meeting(meeting_id) ON DELETE CASCADE
) ENGINE=InnoDB;