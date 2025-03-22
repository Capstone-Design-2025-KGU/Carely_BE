package univ.kgu.carely.domain.member.dto;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import univ.kgu.carely.domain.member.entity.Member;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Member member;

    /**
     * 해당 유저의 권한을 반환한다.
     * 현재는 ROLE_USER를 반환하지만 추후에 Member에 role이 추가되면 해당 role을 리스트의 형태로 보낼 예정임
     * 
     * @return 유저의 권한이 담긴 Collection
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> {return "ROLE_USER";});
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }
}
