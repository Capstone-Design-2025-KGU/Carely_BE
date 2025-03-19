package univ.kgu.carely.domain.member.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.embeded.Address;
import univ.kgu.carely.domain.common.embeded.Skill;
import univ.kgu.carely.domain.common.enums.MemberType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResMemberPrivateInfoDTO {

    private Long memberId;
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private LocalDate birth;
    private String story;
    private MemberType memberType;
    private Boolean isVisible;
    private Boolean isVerified;
    private String profileImage;
    private LocalDateTime createdAt;
    private Address address;
    private Skill skill;
}
