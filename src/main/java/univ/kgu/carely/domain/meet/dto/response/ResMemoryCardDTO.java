package univ.kgu.carely.domain.meet.dto.response;

import java.time.LocalDateTime;
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
public class ResMemoryCardDTO {

    private Long memoryId;
    private MemberType memberType;
    private String oppoName;
    private String oppoMemo;
    private String profileImage;
    private LocalDateTime createdAt;

}
