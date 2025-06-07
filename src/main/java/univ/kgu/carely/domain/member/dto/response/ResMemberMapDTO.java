package univ.kgu.carely.domain.member.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import univ.kgu.carely.domain.common.embeded.skill.Skill;
import univ.kgu.carely.domain.common.enums.MemberType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ResMemberMapDTO {

    private Long memberId;
    private MemberType memberType;
    private BigDecimal lat;
    private BigDecimal lng;

    private String name;
    private Integer withTime;
    private Double distance;
    private Skill skill;

}
