package univ.kgu.carely.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import univ.kgu.carely.domain.member.dto.CustomUserDetails;
import univ.kgu.carely.jwt.dto.LoginDTO;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final long EXPIRED_MS = 600 * 600 * 100000L;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

//        String username = obtainUsername(request);
//        String password = obtainPassword(request);

        String username = null;
        String password = null;

        try {
            LoginDTO loginDTO = objectMapper.readValue(request.getReader().lines().collect(Collectors.joining()), LoginDTO.class);
            username = loginDTO.getUsername();
            password = loginDTO.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password,
                null);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();

        String username = customUserDetails.getUsername();
        String role = authResult.getAuthorities().stream().iterator().next().getAuthority();
        Long memberId = customUserDetails.getMemberId();

        String token = jwtUtil.createJwt(username, role, memberId, EXPIRED_MS);

        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"Bearer " + token + "\"}");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }
}
