package univ.kgu.carely.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import univ.kgu.carely.jwt.JwtFilter;
import univ.kgu.carely.jwt.JwtUtil;
import univ.kgu.carely.jwt.LoginFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(auth->auth.disable());

        http.formLogin(auth->auth.disable());

        http.httpBasic(auth->auth.disable());

        http.cors(custom -> custom.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();

                config.setAllowedOrigins(Collections.singletonList("https://carely.my"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setMaxAge(3600L);
                return config;
            }
        }));

        http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/login", "/", "/api/users/new", "/swagger-ui/**").permitAll()
                        .requestMatchers("/admin").hasRole("USER")
                        .anyRequest().permitAll());

        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);

        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
