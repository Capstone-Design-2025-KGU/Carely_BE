package univ.kgu.carely.domain.common.embeded.skill;

import org.mapstruct.Mapper;
import univ.kgu.carely.domain.member.dto.request.ReqUpdateSkillDTO;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    Skill toEntity(ReqUpdateSkillDTO dto);

}
