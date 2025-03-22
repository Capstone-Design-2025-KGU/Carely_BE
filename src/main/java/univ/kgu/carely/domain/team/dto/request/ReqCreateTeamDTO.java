package univ.kgu.carely.domain.team.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import univ.kgu.carely.domain.common.embeded.Address;

@Getter
@Setter
@NoArgsConstructor
public class ReqCreateTeamDTO {

    private String teamName;
    private Address address;

}
