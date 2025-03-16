package univ.kgu.carely.domain.map.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReqViewPortInfoDTO {

    private BigDecimal lbLat; // left bottom latitude
    private BigDecimal lbLng; // left bottom longitude
    private BigDecimal rtLat; // right top latitude
    private BigDecimal rtLng; // right top longitude

}
