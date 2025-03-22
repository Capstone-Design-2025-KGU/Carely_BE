package univ.kgu.carely.domain.team.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.embeded.Address;

/**
 * 그룹 조회시 List의 형태로 주어지는 간략한 데이터
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResTeamOutlineDTO {

    private Long teamId;
    private String teamName;
    private Address address;
    private Double distance;
    private Long memberCount;

}
