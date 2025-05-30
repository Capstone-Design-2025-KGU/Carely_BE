package univ.kgu.carely.domain.meet.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoSumDTO;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.meet.entity.Memo;
import univ.kgu.carely.domain.member.util.MemberMapper;

@Mapper(componentModel = "spring", uses = MemberMapper.class)
public interface MemoMapper {

    @Mapping(target = "memoId", source = "id")
    ResMemoDTO toResMemoDto(Memo memo);

    Memo updateMemo(@MappingTarget Memo memo, ResMemoSumDTO updatedMemo);
}
