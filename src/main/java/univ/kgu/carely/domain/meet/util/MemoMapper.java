package univ.kgu.carely.domain.meet.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.meet.entity.Memo;

@Mapper(componentModel = "spring")
public interface MemoMapper {

    @Mapping(target = "meeting", source = "meeting")
    @Mapping(target = "member", source = "receiver")
    @Mapping(target = "writer", source = "sender")
    Memo toEntity(Meeting meeting);

}
