package univ.kgu.carely.domain.map.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReqCoordinationDTO {

    private BigDecimal lat;
    private BigDecimal lng;

}
