package univ.kgu.carely.domain.member.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import univ.kgu.carely.domain.common.embeded.Address;
import univ.kgu.carely.domain.common.embeded.Skill;
import univ.kgu.carely.domain.common.enums.MemberType;

/**
 * Member의 isVisible 상태가 true일 때 볼 수 있는 간단한 정보를 담은 DTO
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResMemberPublicInfoDTO {

    private Long memberId;
    private String username;
    private String name;
    private LocalDate birth;
    private String story;
    private MemberType memberType;
    private String profileImage;
    private LocalDateTime createdAt;
    private Address address;
    private Skill skill; // skill 은 MemberType에 따라 null 일 수 있음.
}
