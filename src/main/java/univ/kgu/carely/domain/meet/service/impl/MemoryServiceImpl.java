package univ.kgu.carely.domain.meet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.meet.repository.MemoryRepository;
import univ.kgu.carely.domain.meet.service.MemoryService;
import univ.kgu.carely.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class MemoryServiceImpl implements MemoryService {

    private final MemberService memberService;
    private final MemoryRepository memoryRepository;

    public ResMemoDTO createMemo() {
        return null;
    }

    // Memory = 함께한 추억 = 방명록??
    // 완료된 약속 하나 마다 방명록 작성이 가능한지
    // 아니면 약속은 여러 개가 가능해도 방명록은 하나만 가능한지?
    // 약속과 함께한 추억에 대한 연관관계 설정이 필요한 것 같음.

}
