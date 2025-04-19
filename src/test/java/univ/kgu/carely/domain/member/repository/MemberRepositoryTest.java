package univ.kgu.carely.domain.member.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import univ.kgu.carely.config.QslConfig;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(QslConfig.class)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("쿼리 테스트")
    void getMemberPublicInfo() {
        ResMemberPublicInfoDTO memberPublicInfo = memberRepository.getMemberPublicInfo(1L, 4L);

        System.out.println(memberPublicInfo);

        ResMemberPublicInfoDTO memberPublicInfo1 = memberRepository.getMemberPublicInfo(4L, 1L);

        System.out.println(memberPublicInfo1);
    }
    
    @Test
    @DisplayName("쿼리 테스트")
    void asf() {
        ResMemberPrivateInfoDTO memberPrivateInfo = memberRepository.getMemberPrivateInfo(1L);

        System.out.println(memberPrivateInfo);
    }
}