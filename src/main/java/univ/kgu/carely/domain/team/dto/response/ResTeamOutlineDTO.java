package univ.kgu.carely.domain.team.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "그룹 간단한 정보 응답 DTO", description = "그룹의 간단한 정보를 담아 리스트의 형태로 반환할 때 사용할 응답용 DTO")
public class ResTeamOutlineDTO {

    @Schema(description = "그룹 ID")
    private Long teamId;
    @Schema(description = "그룹 이름")
    private String teamName;
    @Schema(description = "주소")
    private Address address;
    @Schema(description = "거리")
    private Double distance;

    @Schema(description = "회원 수")
    private Long memberCount;

}
