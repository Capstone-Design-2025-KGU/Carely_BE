package univ.kgu.carely.domain.team.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "게시글 수정 요청 DTO", description = "게시글 수정 시 필요한 데이터를 담은 DTO")
public class ReqUpdatePostDTO {

    @Schema(description = "수정할 제목", example = "수정된 제목")
    private String title;
    @Schema(description = "수정할 내용", example = "수정된 내용")
    private String content;

}
