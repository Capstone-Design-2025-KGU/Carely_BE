package univ.kgu.carely.domain.common.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ViewPortInfoDTO {

    private BigDecimal lbLat; // left bottom latitude
    private BigDecimal lbLng; // left bottom longitude
    private BigDecimal rtLat; // right top latitude
    private BigDecimal rtLng; // right top longitude

}
