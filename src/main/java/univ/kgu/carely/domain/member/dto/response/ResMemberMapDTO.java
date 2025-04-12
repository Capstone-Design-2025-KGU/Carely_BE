package univ.kgu.carely.domain.member.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.enums.MemberType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResMemberMapDTO {

    private Long memberId;
    private MemberType memberType;
    private BigDecimal lat;
    private BigDecimal lng;

}
