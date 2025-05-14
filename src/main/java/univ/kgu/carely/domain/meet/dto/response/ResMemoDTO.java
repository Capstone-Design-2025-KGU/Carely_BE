package univ.kgu.carely.domain.meet.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.member.dto.response.ResMemberSmallInfoDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResMemoDTO {

    private Long memoId;
    private String comm;
    private String meal;
    private String toilet;
    private String bath;
    private String walk;
    private LocalDateTime createdAt;
    private ResMemberSmallInfoDTO member;

}
