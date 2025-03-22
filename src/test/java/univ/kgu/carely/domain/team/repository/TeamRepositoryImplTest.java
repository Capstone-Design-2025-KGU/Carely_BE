package univ.kgu.carely.domain.team.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;

@SpringBootTest
@ActiveProfiles("test")
class TeamRepositoryImplTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("")
    void t1() {
        Member member = memberRepository.findById(1L).orElseThrow();
        PageRequest pageRequest = PageRequest.of(0, 5);
        teamRepository.findTeamOutlineWithinDistance(member, 2000, pageRequest);
    }
}