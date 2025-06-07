package univ.kgu.carely.domain.team.repository.team.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.team.dto.response.ResTeamOutlineDTO;
import univ.kgu.carely.domain.team.repository.team.TeamRepository;

@SpringBootTest
class TeamRepositoryImplTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findMyTeamsByAuth() {
        Member member = memberRepository.getReferenceById(1L);

        List<ResTeamOutlineDTO> byAuth = teamRepository.findMyTeamsByAuth(member);
        byAuth.forEach(team-> {
            System.out.println(team.getTeamId());
            System.out.println(team.getTeamName());
            System.out.println(team.getMemberCount());
        });
    }
}