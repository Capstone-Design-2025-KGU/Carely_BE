package univ.kgu.carely.domain.common.embeded.address;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqAddressDTO {

    private String province;
    private String city;
    private String district;
    private String details;
    private BigDecimal latitude;
    private BigDecimal longitude;

}
