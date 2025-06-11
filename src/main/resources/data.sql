INSERT IGNORE INTO member(username,password,name,phone_number,birth,story,member_type,is_visible,is_verified,profile_image,created_at, updated_at, province, city, district,details,lat,lng,location, communication, meal, toilet, bath, walk) VALUES
    ('flutter', '$2a$10$2hxlshzl8gZaE/Eey8CGhOEgNcoqiXXPhm4CUCK4BwkQDo8y6kPq.', '박성민', '010-4989-5600', '2001-02-28', '안녕하세요, 저는 박성민입니다.', 'FAMILY', true,true,'1', now(), now(),'경기도', '수원시', '영통구','경기대 e스퀘어', 37.300627, 127.037393, ST_GeomFromText('POINT(37.300627 127.037393)', 4326), 'HIGH', 'MIDDLE', 'LOW', 'HIGH', 'MIDDLE'),
    ('user2', '$2a$10$HiIQxDqO9EmKHFjxP4jWw.sv5hKxk1uv6RbIo7s5hfVWaKH4O11TO', '조건희', '010-9894-6145', '2001-10-30', '안녕하세요, 저는 조건희입니다.', 'FAMILY', true,true,'3', now(), now(),'경기도', '수원시', '영통구','경기대 8강의동', 37.300781, 127.039357, ST_GeomFromText('POINT(37.300781 127.039357)', 4326), 'LOW', 'HIGH', 'LOW', 'MIDDLE', 'MIDDLE'),
    ('user3', '$2a$10$Wct0z8U/nQmOKG2g22380.1Ru4BMC9BV4IQA3FdDeQhQXGHSpLMoO', '박철민', '010-7584-0912', '1994-09-12', '안녕하세요, 저는 박철민입니다.', 'VOLUNTEER', true,true,'5', now(), now(),'경기도', '수원시', '영통구','경기대 기숙사', 37.297700, 127.038627, ST_GeomFromText('POINT(37.297700 127.038627)', 4326), 'MIDDLE', 'MIDDLE', 'LOW', 'MIDDLE', 'HIGH'),
    ('user4', '$2a$10$0wB0XPj6TUPdY7OUzfNZXO9WC2CAvStUEcH2WLGUhe8xwNN2lcQH6', '우종억', '010-5891-1206', '1968-03-10', '안녕하세요, 저는 우종억입니다.', 'CAREGIVER', true,true,'7', now(), now(),'경기도', '수원시', '영통구','수원 외국어 고등학교', 37.295942, 127.035106, ST_GeomFromText('POINT(37.295942 127.035106)', 4326), 'LOW', 'LOW', 'LOW', 'MIDDLE', 'LOW'),
    ('user5', '$2a$10$ZgkLKXL33o3nlMzkT721k.XuwUGaklmxK1gSLX.QyEARTG2nzNoh2', '김장훈', '010-0594-1278', '1959-07-04', '안녕하세요, 저는 김장훈입니다.', 'FAMILY', true,true,'9', now(), now(),'경기도', '수원시', '영통구','경기대 도서관', 37.301131, 127.035591, ST_GeomFromText('POINT(37.301131 127.035591)', 4326), 'LOW', 'MIDDLE', 'LOW', 'LOW', 'MIDDLE'),
    ('user6', '$2a$10$vg2r6YZzCvLiuPUUcsfr2ORHoDuSJS0yBdx0cAQJ5npQD1xutMFWO', '윤하성', '010-5784-0683', '1998-12-30', '안녕하세요, 저는 윤하성입니다.', 'VOLUNTEER', true,true,'2', now(), now(),'경기도', '수원시', '영통구','경기대 2강의동', 37.299603, 127.033605, ST_GeomFromText('POINT(37.299603 127.033605)', 4326), 'LOW', 'MIDDLE', 'LOW', 'HIGH', 'HIGH'),
    ('user7', '$2a$10$sfx/ynAh5RerwqP4HNfJdOaIenhVXTurf0hQEDTi5JrF/2ndaJeau', '장태수', '010-5940-7895', '1978-04-29', '안녕하세요, 저는 장태수입니다.', 'CAREGIVER', true,true,'4', now(), now(),'경기도', '수원시', '영통구','수원 역사 박물관', 37.297632, 127.035301, ST_GeomFromText('POINT(37.297632 127.035301)', 4326), 'HIGH', 'HIGH', 'LOW', 'HIGH', 'MIDDLE'),
    ('user8', '$2a$10$Hh8edZg.qOm/4wgOzCbWe.Yve7KboyG4sCPqBkfKAFGCqctvSVrJ6', '이정훈', '010-6758-1095', '2000-10-07', '안녕하세요, 저는 이정훈입니다.', 'FAMILY', true,true,'6', now(), now(),'경기도', '수원시', '영통구','창용초등학교', 37.299185, 127.031417, ST_GeomFromText('POINT(37.299185 127.031417)', 4326), 'HIGH', 'LOW', 'LOW', 'MIDDLE', 'MIDDLE'),
    ('user9', '$2a$10$/Xts0FKvsH5VfZ1qsPFXKeofSPjZ1wqZFq2c9CHnmSAikPBuVCHzq', '최은주', '010-1326-7859', '1976-08-23', '안녕하세요, 저는 최은주입니다.', 'VOLUNTEER', true,true,'8', now(), now(),'경기도', '수원시', '영통구','경기대 9강의동', 37.304126, 127.033916, ST_GeomFromText('POINT(37.304126 127.033916)', 4326), 'MIDDLE', 'LOW', 'LOW', 'HIGH', 'HIGH'),
    ('user10', '$2a$10$xBHPX3RIsC7mKz7ltVVbkegwfffMrpYHXUEDMNw/RIpaZFDNX1G.m', '황지민', '010-6098-6859', '1984-07-26', '안녕하세요, 저는 황지민입니다.', 'CAREGIVER', true,true,'10', now(), now(),'경기도', '수원시', '영통구','광교역', 37.302104, 127.044228, ST_GeomFromText('POINT(37.302104 127.044228)', 4326), 'HIGH', 'MIDDLE', 'LOW', 'LOW', 'MIDDLE'),
    ('user11', '$2a$10$B9z7Jip4qjMH3x34noSRdubyTwU9AvEdFfrXIiSdnX/vc94bnOwmm', '백승훈', '010-7812-5742', '1999-04-13', '안녕하세요, 저는 백승훈입니다.', 'FAMILY', true,true,'5', now(), now(),'경기도', '수원시', '영통구','광교 홍재 도서관', 37.302906, 127.047461, ST_GeomFromText('POINT(37.302906 127.047461)', 4326), 'LOW', 'HIGH', 'LOW', 'HIGH', 'MIDDLE'),
    ('user12', '$2a$10$aNtsXbNS8ugDISL/qr0gxuHuTiDme342J9ewf5rqp3gJx4pUlgEH.', '김예진', '010-6039-0852', '2003-09-25', '안녕하세요, 저는 김예진입니다.', 'VOLUNTEER', true,true,'9', now(), now(),'경기도', '수원시', '영통구','광교 중학교', 37.304502, 127.044605, ST_GeomFromText('POINT(37.304502 127.044605)', 4326), 'MIDDLE', 'MIDDLE', 'LOW', 'MIDDLE', 'LOW'),
    ('user13', '$2a$10$SE5mvMQxRyWgTd3TCsE/tO0MguazEqvUr3BAaY7FCbY1gdkzt7b46', '조아라', '010-6079-1273', '1995-12-28', '안녕하세요, 저는 조아라입니다.', 'CAREGIVER', true,true,'3', now(), now(),'경기도', '수원시', '영통구','보훈 재활 체육센터', 37.297572, 127.024646, ST_GeomFromText('POINT(37.297572 127.024646)', 4326), 'HIGH', 'LOW', 'LOW', 'MIDDLE', 'LOW'),
    ('user14', '$2a$10$HKdyJkFlT3LoVMe4f4FwFu4ruVJLUcVHZiEmw3MyLKvNCN9KoC7S6', '신동하', '010-4093-3486', '1990-11-14', '안녕하세요, 저는 신동하입니다.', 'FAMILY', true,true,'4', now(), now(),'경기도', '수원시', '영통구','수원 보훈지청', 37.295925, 127.022575, ST_GeomFromText('POINT(37.295925 127.022575)', 4326), 'LOW', 'MIDDLE', 'LOW', 'LOW', 'MIDDLE'),
    ('user15', '$2a$10$6ekagOI.yvyZ2y/egCUEy.QNMSB1iatLABysisRi/gZ7TeHEjbnPy', '정유진', '010-3095-1782', '2000-05-17', '안녕하세요, 저는 정유진입니다.', 'VOLUNTEER', true,true,'8', now(), now(),'경기도', '수원시', '영통구','광교 공원', 37.301006, 127.030117, ST_GeomFromText('POINT(37.301006 127.030117)', 4326), 'LOW', 'LOW', 'LOW', 'HIGH', 'MIDDLE'),
    ('user16', '$2a$10$sgsD/3x9X58E2qo53pvT1uLYPyJJUHrTFSPbXoby.kw3uH4B/d9jO', '임건우', '010-0695-6859', '2002-12-19', '안녕하세요, 저는 임건우입니다.', 'CAREGIVER', true,true,'2', now(), now(),'경기도', '수원시', '영통구','경기대 e스퀘어', 37.300628, 127.037394, ST_GeomFromText('POINT(37.300628 127.037394)', 4326), 'LOW', 'MIDDLE', 'LOW', 'LOW', 'LOW');

INSERT IGNORE INTO team(team_name, province, city, district, details, lat, lng, location, story, team_image) VALUES
    ('경기대인 모여라', '경기도','수원시','영통구','경기대학교 수원캠퍼스', 37.300627, 127.037393, ST_GeomFromText('POINT(37.300627 127.037393)', 4326), '경기대인의 모임', '1'),
    ('광교공원 요양 팀', '경기도','수원시','영통구','광교공원', 37.301427, 127.030335, ST_GeomFromText('POINT(37.301427 127.030335)', 4326), '광교공원 주변에 사는 사람들 환영합니다','3'),
    ('스위첸 아파트 모임', '경기도','수원시','영통구','서광교파크 스위첸아파트', 37.295012, 127.031257, ST_GeomFromText('POINT(37.295012 127.031257)', 4326), '서광교파크 스위첸 아파트에 사는 간병인들 모임','4'),
    ('포레스트 아파트', '경기도','수원시','영통구','광교역 참누리 포레스트 아파트', 37.302434, 127.039133, ST_GeomFromText('POINT(37.302434 127.039133)', 4326), '포레스트 아파트 단지 내 간병인들 모두 화이팅해요!','2');

INSERT IGNORE INTO team_mate(role, team_id, member_id) VALUES
    ('LEADER', 1, 2),
    ('MATE', 1, 3),
    ('MATE', 1, 4),
    ('LEADER',2, 5),
    ('MATE', 2, 6),
    ('LEADER', 3, 7),
    ('MATE', 3, 8),
    ('MATE', 3, 9),
    ('MATE', 3, 10),
    ('MATE', 3, 11);

INSERT IGNORE INTO post(title, content, member_id, team_id) VALUES
    ('안녕하세요! 잘부탁드립니다!', '20학번 조건희입니다!', 2, 1);

INSERT IGNORE INTO comment(content, post_id, member_id) VALUES
    ('저도 잘부탁드려요', 1, 3),
    ('베릴 ㅎㅇ', 1, 4);

INSERT IGNORE INTO chat_room(room_name) VALUES
    ('1:1 채팅방 1'),
    ('1:1 채팅방 2');

INSERT IGNORE INTO chat_member(member_id, chatroom_id) VALUES
    (1, 1),
    (3, 1),
    (1, 2),
    (4, 2);

INSERT IGNORE INTO meeting(start_time, end_time, chore, status, sender_id, receiver_id) VALUES
    ('2025-06-05 10:00:00','2025-06-05 13:00:00','식사 보조','FINISH', 3, 1),
    ('2025-06-05 13:00:00','2025-06-05 20:00:00','산책 및 저녁 식사 보조','FINISH', 4, 1),
    ('2025-06-06 10:00:00','2025-06-06 13:00:00','식사 보조','FINISH', 3, 1);

INSERT IGNORE INTO memo(comm, meal, toilet, walk, medic, health, member_id) VALUES
    ('딸과 전화 통화를 하면서 웃으셨고, 통화 후 기분이 좋아 보이셨습니다.', '아침과 점심을 거의 다 드셨습니다.', '오늘 화장실에 두 번 가셨고, 소변에는 문제가 없었습니다.', '오늘 마당을 한 바퀴 산책하셨고, 산책 후 컨디션이 좋아 보이셨습니다.', '오전 9시에 약을 복용하셨고, 약 복용을 잊지 않으셨습니다.', '오늘 하루 종일 기침이 없으셨고, 산책 후 컨디션이 좋아 보이셨습니다.', 1);

INSERT IGNORE INTO memory(sender_memo, receiver_memo, sender_id, receiver_id, meeting_id) VALUES
    ('짧은 시간이었지만 함께 어르신을 돌보며 많은 걸 배웠어요. 덕분에 큰 보람을 느낄 수 있었습니다. 늘 힘내세요!', '정보없음', 3, 1, 1),
    ('첫 현장이었는데 많이 긴장했지만 옆에서 챙겨주셔서 큰 도움이 되었어요. 앞으로 더 열심히 배우겠습니다!', '정보없음', 4, 1, 2),
    ('처음이라 서툴렀지만 따뜻하게 대해주셔서 금세 적응할 수 있었어요. 진심으로 감사드립니다!', '정보없음', 3, 1, 3);