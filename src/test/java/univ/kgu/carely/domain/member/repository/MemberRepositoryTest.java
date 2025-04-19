package univ.kgu.carely.domain.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import univ.kgu.carely.config.QslConfig;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(QslConfig.class)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findVisibleByMemberId() {
        ResMemberPublicInfoDTO memberPublicInfo = memberRepository.getMemberPublicInfo(1L, 4L);

        System.out.println(memberPublicInfo);

        ResMemberPublicInfoDTO memberPublicInfo1 = memberRepository.getMemberPublicInfo(4L, 1L);

        System.out.println(memberPublicInfo1);
    }
}