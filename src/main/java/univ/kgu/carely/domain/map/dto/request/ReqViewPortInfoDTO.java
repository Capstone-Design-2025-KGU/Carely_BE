package univ.kgu.carely.domain.map.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(title = "뷰포트 정보 요청 DTO", description = "현재 뷰포트 정보에 대한 정보를 담은 DTO")
public class ReqViewPortInfoDTO {

    @Schema(example = "37.2869235", description = "뷰포트 좌측 하단의 위도")
    private BigDecimal lbLat; // left bottom latitude
    @Schema(example = "127.0190130", description = "뷰포트 좌측 하단의 경도")
    private BigDecimal lbLng; // left bottom longitude
    @Schema(example = "37.3119958", description = "뷰포트 우측 상단의 위도")
    private BigDecimal rtLat; // right top latitude
    @Schema(example = "127.0578250", description = "뷰포트 우측 상단의 경도")
    private BigDecimal rtLng; // right top longitude

}
