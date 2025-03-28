package univ.kgu.carely.domain.member.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.common.embeded.Skill;
import univ.kgu.carely.domain.map.dto.request.ReqCoordinationDTO;
import univ.kgu.carely.domain.member.dto.CustomUserDetails;
import univ.kgu.carely.domain.member.dto.request.ReqMemberCreateDTO;
import univ.kgu.carely.domain.member.dto.request.ReqUpdateSkillDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private static final int VERIFIED_DISTANCE = 50;
    private static final int SEARCH_RANGE = 2000;

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public Member currentMember(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof CustomUserDetails c){
            return memberRepository.findByUsername(c.getUsername());
        }

        return null;
    }

    @Override
    public List<ResMemberPublicInfoDTO> searchNeighborMember() {
        Member member = currentMember();

        if (member == null) {
            throw new RuntimeException("인증된 회원만 이용할 수 있습니다.");
        }

        return memberRepository.findAllWithinDistance(member.getAddress().getLatitude(), member.getAddress().getLongitude(),
                SEARCH_RANGE);
    }

    @Override
    public ResMemberPrivateInfoDTO createMember(ReqMemberCreateDTO reqMemberCreateDTO) {
        Member member = Member.builder()
                .username(reqMemberCreateDTO.getUsername())
                .password(encoder.encode(reqMemberCreateDTO.getPassword()))
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

        return toResMemberPrivateInfoDTO(save);
    }

    private ResMemberPrivateInfoDTO toResMemberPrivateInfoDTO(Member member) {
        return ResMemberPrivateInfoDTO.builder()
                .memberId(member.getMemberId())
                .username(member.getUsername())
                .password(member.getPassword())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .birth(member.getBirth())
                .story(member.getStory())
                .memberType(member.getMemberType())
                .isVisible(member.getIsVisible())
                .isVerified(member.getIsVerified())
                .profileImage(member.getProfileImage())
                .createdAt(member.getCreatedAt())
                .address(member.getAddress())
                .skill(member.getSkill())
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
    public Boolean verifyNeighbor(ReqCoordinationDTO reqCoordinationDTO) {
        Member member = currentMember();

        Double distance = memberRepository.checkVerifiedPlaceWithGPS(member.getMemberId(), reqCoordinationDTO);

        boolean verified = distance <= VERIFIED_DISTANCE;
        if(verified) {
            member.setIsVerified(verified);
            memberRepository.save(member);
        }

        return verified; // 50m 안에 해당하는지 확인
    }

    @Override
    public ResMemberPrivateInfoDTO getPrivateInfo(){
        Member member = currentMember();

        return toResMemberPrivateInfoDTO(member);
    }

    @Override
    @Transactional
    public Boolean updateSkill(ReqUpdateSkillDTO reqUpdateSkillDTO){
        Member member = currentMember();

        Skill skill = member.getSkill();
        skill.setCommunication(reqUpdateSkillDTO.getCommunication());
        skill.setMeal(reqUpdateSkillDTO.getMeal());
        skill.setToilet(reqUpdateSkillDTO.getToilet());
        skill.setBath(reqUpdateSkillDTO.getBath());
        skill.setWalk(reqUpdateSkillDTO.getWalk());

        memberRepository.save(member);

        return true;
    }

}
