package univ.kgu.carely.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.embeded.address.Address;
import univ.kgu.carely.domain.common.embeded.Skill;
import univ.kgu.carely.domain.common.embeded.address.ReqAddressDTO;
import univ.kgu.carely.domain.common.enums.MemberType;

@Getter
@Setter
@NoArgsConstructor
@Schema(title = "회원가입 요청 DTO", description = "회원가입 할 때 필요한 정보를 담은 DTO")
public class ReqMemberCreateDTO {

    @Schema(description = "회원 ID", example = "qwer1234")
    private String username;
    @Schema(description = "회원 비밀번호", example = "asdf1234")
    private String password;
    @Schema(description = "회원 이름", example = "조건희")
    private String name;
    @Schema(description = "회원 전화번호", example = "010-5678-1234")
    private String phoneNumber;
    @Schema(description = "회원 생일", example = "2001-10-30")
    private LocalDate birth;
    @Schema(description = "회원 소개 문구", example = "안녕하세요!")
    private String story;
    @Schema(description = "회원 타입", example = "FAMILY")
    private MemberType memberType;
    @Schema(description = "회원 공개 여부", example = "true")
    private Boolean isVisible;
    private ReqAddressDTO address;
    private Skill skill;

}
