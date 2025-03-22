package univ.kgu.carely.domain.team.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.embeded.Address;

@Getter
@Setter
@NoArgsConstructor
@Schema(title = "그룹 생성 요청 DTO", description = "그룹 생성할 때 필요한 정보를 요청하는 DTO")
public class ReqCreateTeamDTO {

    @Schema(description = "그룹의 이름", example = "경기대 학생들 모임")
    private String teamName;
    @Schema(description = "그룹의 중심 주소", example = "그룹 주소")
    private Address address;

}
