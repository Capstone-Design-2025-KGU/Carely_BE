package univ.kgu.carely.domain.team.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.team.dto.response.ResTeamOutlineDTO;
import univ.kgu.carely.domain.team.repository.team.TeamRepository;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class TeamRepositoryImplTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("팀 정보 페이징 테스트")
    void t1() {
        Member member = memberRepository.findById(1L).orElseThrow();

        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<ResTeamOutlineDTO> teamOutlineWithinDistance = teamRepository.findTeamOutlineWithinDistance(member, 2000,
                pageRequest);

        assertThat(teamOutlineWithinDistance.getNumberOfElements()).isEqualTo(2);
        assertThat(teamOutlineWithinDistance.getContent().get(0).getDistance()).isLessThan(
                teamOutlineWithinDistance.getContent().get(1).getDistance());
        assertThat(teamOutlineWithinDistance.getContent().get(0).getMemberCount()).isEqualTo(1);
        assertThat(teamOutlineWithinDistance.getContent().get(1).getMemberCount()).isEqualTo(4);
    }
}