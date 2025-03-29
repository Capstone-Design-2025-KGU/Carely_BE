package univ.kgu.carely.domain.team.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.member.dto.response.ResMemberSmallInfoDTO;

/**
 * 게시글의 댓글 내용을 담은 Response DTO.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "댓글 응답 DTO", description = "댓글 응답용 DTO")
public class ResCommentDTO {
    
    @Schema(description = "댓글 ID")
    private Long commentId;
    @Schema(description = "댓글 내용")
    private String content;
    @Schema(description = "댓글 작성 일자")
    private LocalDateTime createdAt;
    @Schema(description = "댓글 작성자 정보")
    private ResMemberSmallInfoDTO writer;

}
