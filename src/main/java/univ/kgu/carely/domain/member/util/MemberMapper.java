package univ.kgu.carely.domain.member.util;

import java.time.LocalDate;
import java.time.Period;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import univ.kgu.carely.domain.member.dto.request.ReqMemberCreateDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberSmallInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    @Mapping(target = "address", ignore = true)
    @Mapping(target = "password", ignore = true)
    Member toEntity(ReqMemberCreateDTO dto);

    @Mapping(target = "withTimeSum", constant = "0")
    @Mapping(target = "age", source = "birth", qualifiedByName = "calculateAge")
    ResMemberPrivateInfoDTO toResMemberPrivateInfoDto(Member member);

    ResMemberSmallInfoDTO toResMemberSmallInfoDto(Member member);

    @Named("calculateAge")
    default Integer calculateAge(LocalDate birth) {
        System.out.println(birth);
        if (birth == null)
            return null;
        return Period.between(birth, LocalDate.now()).getYears();
    }

}
