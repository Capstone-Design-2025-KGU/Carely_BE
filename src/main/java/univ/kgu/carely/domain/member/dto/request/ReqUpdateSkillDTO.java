package univ.kgu.carely.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.enums.SkillLevel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqUpdateSkillDTO {

    @Schema(description = "대화 능력")
    private SkillLevel communication;
    @Schema(description = "식사 능력")
    private SkillLevel meal;
    @Schema(description = "화장실 보조 능력")
    private SkillLevel toilet;
    @Schema(description = "씻기기 능력")
    private SkillLevel bath;
    @Schema(description = "걷기 보조 능력")
    private SkillLevel walk;
}
