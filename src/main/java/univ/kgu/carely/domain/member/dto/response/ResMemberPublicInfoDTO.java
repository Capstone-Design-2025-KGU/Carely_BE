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

/**
 * Member의 isVisible 상태가 true일 때 볼 수 있는 간단한 정보를 담은 DTO
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "멤버의 공개 정보 응답 DTO", description = "멤버의 공개된 정보를 응답할 때 사용하는 DTO")
public class ResMemberPublicInfoDTO {

    @Schema(description = "멤버 고유 ID")
    private Long memberId;
    @Schema(description = "멤버 로그인 ID")
    private String username;
    @Schema(description = "멤버 이름")
    private String name;
    @Schema(description = "멤버 생일")
    private LocalDate birth;
    @Schema(description = "멤버 스토리")
    private String story;
    @Schema(description = "멤버 타입")
    private MemberType memberType;
    @Schema(description = "프로필 이미지 주소")
    private String profileImage;
    @Schema(description = "멤버 생성 일시")
    private LocalDateTime createdAt;
    @Schema(description = "거리")
    private Double distance;
    @Schema(description = "멤버 주소")
    private Address address;
    @Schema(description = "멤버 기술")
    private Skill skill; // skill 은 MemberType에 따라 null 일 수 있음.
}
