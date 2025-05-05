package univ.kgu.carely.domain.meet.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.meet.entity.Memory;

@Mapper(componentModel = "spring")
public interface MemoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "meeting", source = "meeting")
    Memory toEntity(Meeting meeting);

}
