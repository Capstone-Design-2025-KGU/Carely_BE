package univ.kgu.carely.domain.team.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.member.dto.response.ResMemberSmallInfoDTO;

/**
 * 게시글 내용을 담은 Response DTO.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "게시글 응답 DTO", description = "게시글 응답용 DTO")
public class ResPostDTO {

    @Schema(description = "게시글 ID")
    private Long postId;
    @Schema(description = "게시글 제목")
    private String title;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 작성 일시")
    private LocalDateTime createdAt;
    @Schema(description = "게시글 작성자 정보")
    private ResMemberSmallInfoDTO writer;
    @Schema(description = "댓글")
    private List<ResCommentDTO> resCommentDTOS;

}
