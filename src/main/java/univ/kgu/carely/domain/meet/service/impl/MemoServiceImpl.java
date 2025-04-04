package univ.kgu.carely.domain.meet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ.kgu.carely.domain.meet.repository.MemoRepository;
import univ.kgu.carely.domain.meet.service.MemoService;
import univ.kgu.carely.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemberService memberService;
    private final MemoRepository memoRepository;

    // 메모는 언제 작성이 가능한가? 약속이 수락 된 경우?
    // 메모는 가족 간병인이 미리 적어놓는 것??
    // 메모는 누가 열람이 가능한가? 해당 사람과 약속이 수락된 사람?
    // 메모는 계속 쌓이는 데이터인가? 아니면

}
