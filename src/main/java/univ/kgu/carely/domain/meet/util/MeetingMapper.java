package univ.kgu.carely.domain.meet.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import univ.kgu.carely.domain.meet.dto.request.ReqMeetingCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMeetingDTO;
import univ.kgu.carely.domain.meet.entity.Meeting;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.util.MemberMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MemberMapper.class)
public interface MeetingMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "memos", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    Meeting toEntity(ReqMeetingCreateDTO dto, Member sender, Member receiver);

    @Mapping(target = "meetingId", source = "id")
    ResMeetingDTO toResMeetingDto(Meeting meeting);

}
