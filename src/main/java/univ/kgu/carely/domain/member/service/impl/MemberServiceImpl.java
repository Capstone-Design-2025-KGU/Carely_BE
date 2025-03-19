package univ.kgu.carely.domain.member.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.map.dto.request.ReqCoordinationDTO;
import univ.kgu.carely.domain.map.dto.request.ReqViewPortInfoDTO;
import univ.kgu.carely.domain.member.dto.request.ReqMemberCreateDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
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

    @Override
    public ResMemberPrivateInfoDTO createMember(ReqMemberCreateDTO reqMemberCreateDTO) {
        Member member = Member.builder()
                .username(reqMemberCreateDTO.getUsername())
                .password(reqMemberCreateDTO.getPassword())
                .name(reqMemberCreateDTO.getName())
                .phoneNumber(reqMemberCreateDTO.getPhoneNumber())
                .birth(reqMemberCreateDTO.getBirth())
                .story(reqMemberCreateDTO.getStory())
                .memberType(reqMemberCreateDTO.getMemberType())
                .isVisible(reqMemberCreateDTO.getIsVisible())
                .address(reqMemberCreateDTO.getAddress())
                .skill(reqMemberCreateDTO.getSkill())
                .build();

        Member save = memberRepository.save(member);

        return ResMemberPrivateInfoDTO.builder()
                .memberId(save.getMemberId())
                .username(save.getUsername())
                .password(save.getPassword())
                .name(save.getName())
                .phoneNumber(save.getPhoneNumber())
                .birth(save.getBirth())
                .story(save.getStory())
                .memberType(save.getMemberType())
                .isVisible(save.getIsVisible())
                .isVerified(save.getIsVerified())
                .profileImage(save.getProfileImage())
                .createdAt(save.getCreatedAt())
                .address(save.getAddress())
                .skill(save.getSkill())
                .build();
    }

    @Override
    public Boolean isDuplicatedUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    @Override
    public Boolean isDuplicatedPhoneNumber(String phoneNumber){
        return memberRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean verifyNeighbor(Long memberId, ReqCoordinationDTO reqCoordinationDTO) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        Double distance = memberRepository.checkVerifiedPlaceWithGPS(member.getMemberId(), reqCoordinationDTO);

        boolean verified = distance <= 50;
        if(verified) {
            member.setIsVerified(verified);
            memberRepository.save(member);
        }

        return verified; // 50m 안에 해당하는지 확인
    }
}
