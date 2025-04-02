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

}
