package univ.kgu.carely.domain.member.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.common.embeded.Skill;
import univ.kgu.carely.domain.common.embeded.address.Address;
import univ.kgu.carely.domain.common.embeded.address.ReqAddressDTO;
import univ.kgu.carely.domain.map.dto.request.ReqCoordinationDTO;
import univ.kgu.carely.domain.member.dto.request.ReqMemberCreateDTO;
import univ.kgu.carely.domain.member.dto.request.ReqUpdateSkillDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberMapDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberSmallInfoDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.member.service.MemberService;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private static final int VERIFIED_DISTANCE = 50;
    private static final int SEARCH_RANGE = 2000;

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    private final GeometryFactory gf;

    @Override
    @Transactional(readOnly = true)
    public List<ResMemberMapDTO> searchNeighborMember(Member member, String query) {
        member = memberRepository.findById(member.getMemberId()).orElseThrow();
        BigDecimal memberLat = member.getAddress().getLatitude();
        BigDecimal memberLng = member.getAddress().getLongitude();

        Point point = gf.createPoint(new Coordinate(memberLng.doubleValue(), memberLat.doubleValue()));

        return memberRepository.findAllWithinDistance(query, point, SEARCH_RANGE);
    }

    @Override
    public ResMemberPrivateInfoDTO createMember(ReqMemberCreateDTO reqMemberCreateDTO) {
        ReqAddressDTO address = reqMemberCreateDTO.getAddress();
        Address fullAddress = Address.builder()
                .province(address.getProvince())
                .city(address.getCity())
                .district(address.getDistrict())
                .details(address.getDetails())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .location(gf.createPoint(new Coordinate(address.getLongitude().doubleValue(),
                        address.getLatitude().doubleValue())))
                .build();

        Member member = Member.builder()
                .username(reqMemberCreateDTO.getUsername())
                .password(encoder.encode(reqMemberCreateDTO.getPassword()))
                .name(reqMemberCreateDTO.getName())
                .phoneNumber(reqMemberCreateDTO.getPhoneNumber())
                .birth(reqMemberCreateDTO.getBirth())
                .story(reqMemberCreateDTO.getStory())
                .memberType(reqMemberCreateDTO.getMemberType())
                .isVisible(reqMemberCreateDTO.getIsVisible())
                .address(fullAddress)
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
    public Boolean isDuplicatedPhoneNumber(String phoneNumber) {
        return memberRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean verifyNeighbor(Member member, ReqCoordinationDTO reqCoordinationDTO) {
        member = memberRepository.findById(member.getMemberId()).orElseThrow();

        Point point = gf.createPoint(
                new Coordinate(reqCoordinationDTO.getLng().doubleValue(), reqCoordinationDTO.getLat().doubleValue()));

        Double distance = memberRepository.checkVerifiedPlaceWithGPS(member.getMemberId(), point);

        boolean verified = distance <= VERIFIED_DISTANCE;
        if (verified) {
            member.setIsVerified(verified);
            memberRepository.save(member);
        }

        return verified; // 50m 안에 해당하는지 확인
    }

    @Override
    @Transactional(readOnly = true)
    public ResMemberPrivateInfoDTO getPrivateInfo(Member member){
        member = memberRepository.findById(member.getMemberId()).orElseThrow();

        return toResMemberPrivateInfoDTO(member);
    }

    @Override
    @Transactional
    public Boolean updateSkill(Member member, ReqUpdateSkillDTO reqUpdateSkillDTO) {
        member = memberRepository.findById(member.getMemberId()).orElseThrow();

        Skill skill = member.getSkill();
        skill.setCommunication(reqUpdateSkillDTO.getCommunication());
        skill.setMeal(reqUpdateSkillDTO.getMeal());
        skill.setToilet(reqUpdateSkillDTO.getToilet());
        skill.setBath(reqUpdateSkillDTO.getBath());
        skill.setWalk(reqUpdateSkillDTO.getWalk());

        memberRepository.save(member);

        return true;
    }

    @Override
    public ResMemberSmallInfoDTO toResMemberSmallInfoDTO(Member member) {
        return ResMemberSmallInfoDTO.builder()
                .memberId(member.getMemberId())
                .username(member.getUsername())
                .name(member.getName())
                .memberType(member.getMemberType())
                .profileImage(member.getProfileImage())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ResMemberPublicInfoDTO getMemberPublicInfo(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        if (!member.getIsVisible()) {
            throw new RuntimeException("해당 멤버는 비공개상태 입니다.");
        }

        return toResMemberPublicInfoDTO(member);
    }

    @Override
    public ResMemberPublicInfoDTO toResMemberPublicInfoDTO(Member member) {
        return ResMemberPublicInfoDTO.builder()
                .memberId(member.getMemberId())
                .username(member.getUsername())
                .name(member.getName())
                .birth(member.getBirth())
                .story(member.getStory())
                .memberType(member.getMemberType())
                .profileImage(member.getProfileImage())
                .createdAt(member.getCreatedAt())
                .address(member.getAddress())
                .skill(member.getSkill())
                .build();
    }

}
