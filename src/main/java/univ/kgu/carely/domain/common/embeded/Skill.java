package univ.kgu.carely.domain.common.embeded;

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
public class Skill {

    @Enumerated(EnumType.STRING)
    private SkillLevel communication;

    @Enumerated(EnumType.STRING)
    private SkillLevel meal;

    @Enumerated(EnumType.STRING)
    private SkillLevel toilet;

    @Enumerated(EnumType.STRING)
    private SkillLevel bath;

    @Enumerated(EnumType.STRING)
    private SkillLevel walk;
}
