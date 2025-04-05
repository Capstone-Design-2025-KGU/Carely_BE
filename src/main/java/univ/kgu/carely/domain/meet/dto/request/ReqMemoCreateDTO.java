package univ.kgu.carely.domain.meet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqMemoCreateDTO {

    private String comm;
    private String meal;
    private String toilet;
    private String bath;
    private String walk;

}
