package univ.kgu.carely.domain.meet.service;

import univ.kgu.carely.domain.meet.dto.request.ReqMemoUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.member.entity.Member;

public interface MemoService {
    ResMemoDTO updateMemo(Long memberId, Member member, ReqMemoUpdateDTO reqMemoUpdateDTO);

    ResMemoDTO readCurrentFinishedMemo(Member member, Long memberId);

    ResMemoDTO readMemo(Member member, Long memberId);
}
