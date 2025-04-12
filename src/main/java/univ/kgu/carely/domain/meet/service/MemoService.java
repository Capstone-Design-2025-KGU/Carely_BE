package univ.kgu.carely.domain.meet.service;

import univ.kgu.carely.domain.meet.dto.request.ReqMemoUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.meet.entity.Memo;
import univ.kgu.carely.domain.member.entity.Member;

public interface MemoService {
    ResMemoDTO updateMemo(Member member, Long meetingId, ReqMemoUpdateDTO reqMemoUpdateDTO);

    ResMemoDTO readCurrentFinishedMemo(Member member, Long memberId);

    ResMemoDTO toResMemoDTO(Memo memo);

    ResMemoDTO readMemo(Member member, Long meetingId);
}
