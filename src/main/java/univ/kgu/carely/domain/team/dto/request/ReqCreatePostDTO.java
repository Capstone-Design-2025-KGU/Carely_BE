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
@Schema(title = "게시글 작성 요청 DTO", description = "게시글을 작성할 때 필요한 정보를 담은 DTO")
public class ReqCreatePostDTO {
    
    @Schema(description = "게시글 제목", example = "제목")
    private String title;
    @Schema(description = "게시글 내용", example = "내용")
    private String content;

}
