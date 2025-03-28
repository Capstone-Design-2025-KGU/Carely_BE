package univ.kgu.carely.domain.chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.enums.MemberType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "채팅방 응답 DTO")
public class ChatRoomResponse {

    @Schema(description = "회원 ID", example = "1")
    private Long memberId;

    @Schema(description = "회원 이름", example = "김상덕")
    private String memberName;

    @Schema(description = "회원 유형", example = "FAMILY")
    private MemberType memberType;

    @Schema(description = "프로필 이미지 파일명", example = "1")
    private String profileImage;

    @Schema(description = "채팅방 ID", example = "1")
    private Long chatRoomId;

    @Schema(description = "가장 최근 메시지 내용", example = "형제들이여")
    private String content;

    @Schema(description = "채팅방 참여자 수", example = "3")
    private int participantCount;

    @Schema(description = "가장 최근 메시지의 생성 시간", example = "2025-03-21T17:45:34.658")
    private LocalDateTime createdAt;
}
