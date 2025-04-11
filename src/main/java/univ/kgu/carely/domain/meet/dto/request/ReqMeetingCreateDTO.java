package univ.kgu.carely.domain.meet.dto.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReqMeetingCreateDTO {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String chore;

}
