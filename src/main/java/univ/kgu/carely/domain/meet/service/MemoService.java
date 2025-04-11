package univ.kgu.carely.domain.meet.service;

import univ.kgu.carely.domain.meet.dto.request.ReqMemoUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.meet.entity.Memo;

public interface MemoService {
    ResMemoDTO updateMemo(Long meetingId, ReqMemoUpdateDTO reqMemoUpdateDTO);

    ResMemoDTO readCurrentFinishedMemo(Long memberId);

    ResMemoDTO toResMemoDTO(Memo memo);

    ResMemoDTO readMemo(Long meetingId);
}
