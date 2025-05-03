package univ.kgu.carely.domain.team.util;

import org.mapstruct.Mapper;
import univ.kgu.carely.domain.common.embeded.address.util.AddressMapper;
import univ.kgu.carely.domain.team.dto.request.ReqCreateTeamDTO;
import univ.kgu.carely.domain.team.dto.response.ResTeamOutlineDTO;
import univ.kgu.carely.domain.team.entity.Team;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface TeamMapper {

    Team toEntity(ReqCreateTeamDTO dto);

    ResTeamOutlineDTO toResTeamOutlineDto(Team team);

}
