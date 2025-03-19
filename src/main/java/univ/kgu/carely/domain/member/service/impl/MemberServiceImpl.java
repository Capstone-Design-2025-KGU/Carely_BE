package univ.kgu.carely.domain.member.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.map.dto.request.ReqViewPortInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<ResMemberPublicInfoDTO> searchNeighborMember(Long memberId, ReqViewPortInfoDTO viewPortDTO,
                                                             MemberType memberType) {
        Member me = memberRepository.findById(memberId).orElseThrow();

        if (!me.getIsVerified()) {
            throw new RuntimeException("인증된 회원만 이용할 수 있습니다.");
        }

        return memberRepository.findAllWithinDistance(me.getAddress().getLatitude(), me.getAddress().getLongitude(),
                2000, viewPortDTO, memberType);
    }

}
