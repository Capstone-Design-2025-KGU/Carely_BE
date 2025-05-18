package univ.kgu.carely.domain.meet.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ResMemoSumDTO {

    private String health;
    private String medic;
    private String meal;
    private String walk;
    private String comm;
    private String toilet;

}
