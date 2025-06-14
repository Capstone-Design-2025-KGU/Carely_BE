package univ.kgu.carely.domain.member.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.common.embeded.skill.Skill;
import univ.kgu.carely.domain.common.embeded.address.Address;
import univ.kgu.carely.domain.common.embeded.address.ReqAddressDTO;
import univ.kgu.carely.domain.common.embeded.address.util.AddressMapper;
import univ.kgu.carely.domain.common.embeded.skill.util.SkillMapper;
import univ.kgu.carely.domain.map.dto.request.ReqCoordinationDTO;
import univ.kgu.carely.domain.meet.entity.Memo;
import univ.kgu.carely.domain.member.dto.request.ReqMemberCreateDTO;
import univ.kgu.carely.domain.member.dto.request.ReqUpdateSkillDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberMapDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPrivateInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMemberPublicInfoDTO;
import univ.kgu.carely.domain.member.dto.response.ResMembersRecommendedDTO;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.repository.MemberRepository;
import univ.kgu.carely.domain.member.service.MemberService;
import univ.kgu.carely.domain.member.util.MemberMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private static final int VERIFIED_DISTANCE = 50;
    private static final int SEARCH_RANGE = 2000;

    private final MemberRepository memberRepository;

    private final AddressMapper addressMapper;
    private final MemberMapper memberMapper;
    private final SkillMapper skillMapper;

    private final BCryptPasswordEncoder encoder;
    private final GeometryFactory gf;

    @Override
    @Transactional(readOnly = true)
    public List<ResMemberMapDTO> searchNeighborMember(Member member, String query) {
        return memberRepository.findAllWithinDistance(query, SEARCH_RANGE, member);
    }

    @Override
    @Transactional
    public ResMemberPrivateInfoDTO createMember(ReqMemberCreateDTO reqMemberCreateDTO) {
        ReqAddressDTO address = reqMemberCreateDTO.getAddress();
        Address fullAddress = addressMapper.toEntity(address);
        fullAddress.setLocation(gf.createPoint(
                new Coordinate(address.getLongitude().doubleValue(), address.getLatitude().doubleValue())));

        Member member = memberMapper.toEntity(reqMemberCreateDTO);
        member.setPassword(encoder.encode(reqMemberCreateDTO.getPassword()));
        member.setAddress(fullAddress);

        Member save = memberRepository.save(member);
        Memo memo = new Memo();
        memo.setMember(save);
        save.getMemos().add(memo);

        return memberMapper.toResMemberPrivateInfoDto(save);
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
    public ResMemberPrivateInfoDTO getPrivateInfo(Member member) {
        ResMemberPrivateInfoDTO privateInfo = memberRepository.getMemberPrivateInfo(member.getMemberId());
        privateInfo.setAge(Period.between(privateInfo.getBirth(), LocalDate.now()).getYears());
        return privateInfo;
    }

    @Override
    @Transactional
    public Skill updateSkill(Member member, ReqUpdateSkillDTO reqUpdateSkillDTO) {
        member = memberRepository.getReferenceById(member.getMemberId());

        Skill skill = skillMapper.toEntity(reqUpdateSkillDTO);
        member.setSkill(skill);
        memberRepository.save(member);

        return skill;
    }

    @Override
    @Transactional(readOnly = true)
    public ResMemberPublicInfoDTO getMemberPublicInfo(Long opponentMemberId, Member self) {
        Member member = memberRepository.findById(opponentMemberId).orElseThrow();

        if (!member.getIsVisible()) {
            throw new RuntimeException("해당 멤버는 비공개상태 입니다.");
        }

        return memberRepository.getMemberPublicInfo(opponentMemberId, self.getMemberId());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResMembersRecommendedDTO> getRecommendedNeighbors(Pageable pageable, Member member) {
        member = memberRepository.findById(member.getMemberId()).orElseThrow();

        return memberRepository.findRecommendedMembers(SEARCH_RANGE, member, pageable);
    }

}
