package univ.kgu.carely.domain.common.embeded;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "주소", description = "주소 정보")
public class Address {

    @Schema(description = "도")
    private String province;
    @Schema(description = "시")
    private String city;
    @Schema(description = "구")
    private String district;
    @Schema(description = "세부 주소")
    private String details;
    @Schema(description = "위도")
    @Column(name = "lat", precision = 10, scale = 7, nullable = false)
    private BigDecimal latitude;
    @Schema(description = "경도")
    @Column(name = "lng", precision = 10, scale = 7, nullable = false)
    private BigDecimal longitude;
}
