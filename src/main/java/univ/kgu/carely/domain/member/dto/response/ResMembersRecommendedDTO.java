package univ.kgu.carely.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.enums.MemberType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResMembersRecommendedDTO {

    private Long memberId;
    private String name;
    private String profileImage;
    private MemberType memberType;
    private Double distance;
    private Integer withTime;

}
