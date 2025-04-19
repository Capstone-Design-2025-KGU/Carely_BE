package univ.kgu.carely.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.embeded.address.Address;
import univ.kgu.carely.domain.common.embeded.Skill;
import univ.kgu.carely.domain.common.enums.MemberType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "회원 개인 정보 응답 DTO", description = "회원 개인 정보 응답 DTO")
public class ResMemberPrivateInfoDTO {

    @Schema(description = "고유 멤버 ID")
    private Long memberId;
    @Schema(description = "회원 ID")
    private String username;
    @Schema(description = "회원 이름")
    private String name;
    @Schema(description = "회원 전화번호")
    private String phoneNumber;
    @Schema(description = "회원 생일")
    private LocalDate birth;
    @Schema(description = "회원의 소개 문구")
    private String story;
    @Schema(description = "회원 타입")
    private MemberType memberType;
    @Schema(description = "회원의 공개 여부")
    private Boolean isVisible;
    @Schema(description = "회원의 동네 인증 여부")
    private Boolean isVerified;
    @Schema(description = "회원의 프로필 사진 경로")
    private String profileImage;
    @Schema(description = "회원 생성 일시")
    private LocalDateTime createdAt;
    @Schema(description = "요양 총 시간")
    private Integer withTimeSum;
    @Schema(description = "회원 주소")
    private Address address;
    @Schema(description = "회원 보조 기술")
    private Skill skill;
}
