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
public class ResMemoryDTO {

    private Long memoryId;
    private String senderMemo;
    private String receiverMemo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ResMemberSmallInfoDTO sender;
    private ResMemberSmallInfoDTO receiver;
    private Long meetingId;

}
