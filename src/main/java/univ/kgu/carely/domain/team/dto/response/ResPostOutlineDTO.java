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
 * 간단한 게시글 정보를 담은 Response DTO.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(title = "게시글의 간단한 정보 응답 DTO", description = "게시글을 리스트의 형태로 조회할 때 사용될 간단한 정보를 담은 응답용 DTO")
public class ResPostOutlineDTO {

    @Schema(description = "게시글 ID")
    private Long postId;
    @Schema(description = "게시글 제목")
    private String title;
    @Schema(description = "게시글 작성 일시")
    private LocalDateTime createdAt;
    @Schema(description = "게시글의 댓글 수")
    private Long commentCount;
    @Schema(description = "게시글 작성자 정보")
    private ResMemberSmallInfoDTO writer;

}
