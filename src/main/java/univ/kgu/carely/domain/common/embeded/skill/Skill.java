package univ.kgu.carely.domain.common.embeded.skill;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.enums.SkillLevel;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "보조 기술", description = "기술 수준")
public class Skill {

    @Schema(description = "대화 능력")
    @Enumerated(EnumType.STRING)
    private SkillLevel communication;
    @Schema(description = "식사 능력")
    @Enumerated(EnumType.STRING)
    private SkillLevel meal;
    @Schema(description = "화장실 보조 능력")
    @Enumerated(EnumType.STRING)
    private SkillLevel toilet;
    @Schema(description = "씻기기 능력")
    @Enumerated(EnumType.STRING)
    private SkillLevel bath;
    @Schema(description = "걷기 보조 능력")
    @Enumerated(EnumType.STRING)
    private SkillLevel walk;
}
