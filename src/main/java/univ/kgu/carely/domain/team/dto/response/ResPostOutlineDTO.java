package univ.kgu.carely.domain.team.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.member.dto.response.ResMemberSmallInfoDTO;

/**
 * 간단한 게시글 정보를 담은 Response DTO.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResPostOutlineDTO {

    private Long postId;
    private String title;
    private LocalDateTime createdAt;
    private ResMemberSmallInfoDTO writer;

}
