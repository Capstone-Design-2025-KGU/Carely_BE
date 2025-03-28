package univ.kgu.carely.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.enums.MemberType;

/**
 * 간단한 유저 정보를 담은 Response DTO.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResMemberSmallInfoDTO {

    private Long memberId;
    private String username;
    private String name;
    private MemberType memberType;
    private String profileImage;

}
