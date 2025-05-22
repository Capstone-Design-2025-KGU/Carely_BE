package univ.kgu.carely.domain.meet.dto.response;

import java.time.LocalDate;
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
public class ResMemoryCardDTO {

    private Long memoryId;
    private String oppoName;
    private LocalDate createdAt;
    private String memo;

}
