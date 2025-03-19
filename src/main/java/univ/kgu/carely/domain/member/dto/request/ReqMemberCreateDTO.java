package univ.kgu.carely.domain.member.dto.request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.embeded.Address;
import univ.kgu.carely.domain.common.embeded.Skill;
import univ.kgu.carely.domain.common.enums.MemberType;

@Getter
@Setter
@NoArgsConstructor
public class ReqMemberCreateDTO {

    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private LocalDate birth;
    private String story;
    private MemberType memberType;
    private Boolean isVisible;
    private Address address;
    private Skill skill;

}
