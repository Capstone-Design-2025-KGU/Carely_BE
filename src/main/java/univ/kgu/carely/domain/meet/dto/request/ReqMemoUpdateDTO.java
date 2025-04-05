package univ.kgu.carely.domain.meet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqMemoUpdateDTO {

    private String comm;
    private String meal;
    private String toilet;
    private String bath;
    private String walk;

}
