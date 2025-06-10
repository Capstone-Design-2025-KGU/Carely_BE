package univ.kgu.carely.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class SecurityConfigTest {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    void bCryptPasswordEncoder() {
        for(int i=1;i<=16;i++){
            String s = encoder.encode("pass"+i);
            System.out.println(s);
        }
    }
}