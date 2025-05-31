package univ.kgu.carely.domain.meet.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ResMeetingSmallInfoDTO {

    private Long meetingId;
    private ResMemberSmallInfoDTO receiver;
    private ResMemberSmallInfoDTO sender;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    private Long memoId;
    private String walk;
    private String health;
    private String medic;
    private String toilet;
    private String comm;
    private String meal;

}
