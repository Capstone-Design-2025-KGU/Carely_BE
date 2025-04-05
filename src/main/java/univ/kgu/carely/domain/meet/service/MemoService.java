package univ.kgu.carely.domain.meet.service;

import univ.kgu.carely.domain.meet.dto.request.ReqMemoUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.meet.entity.Memo;

public interface MemoService {
    ResMemoDTO updateMemo(Long memoId, ReqMemoUpdateDTO reqMemoUpdateDTO);

    ResMemoDTO readCurrentMemo(Long memberId);

    ResMemoDTO toResMemoDTO(Memo memo);
}
