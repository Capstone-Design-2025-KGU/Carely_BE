package univ.kgu.carely.domain.map.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(title = "좌표 요청 DTO", description = "위도/경도 기반 좌표계")
public class ReqCoordinationDTO {

    @Schema(description = "위도", example = "37.3003077")
    private BigDecimal lat;
    @Schema(description = "경도", example = "127.0399191")
    private BigDecimal lng;

}
