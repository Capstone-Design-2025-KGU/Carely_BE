package univ.kgu.carely.domain.common.embeded;

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
public class Address {

    private String province;

    private String city;

    private String district;

    @Column(name = "lat", precision = 10, scale = 7, nullable = false)
    private BigDecimal latitude;

    @Column(name = "lng", precision = 10, scale = 7, nullable = false)
    private BigDecimal longitude;
}
