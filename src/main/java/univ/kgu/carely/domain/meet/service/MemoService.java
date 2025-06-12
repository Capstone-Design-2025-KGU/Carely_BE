package univ.kgu.carely.domain.meet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoDTO;
import univ.kgu.carely.domain.member.entity.Member;

public interface MemoService {
    ResMemoDTO updateMemo(Long memberId, Member auth, ReqMemoUpdateDTO reqMemoUpdateDTO);

    Page<ResMemoDTO> readMemo(Long memberId, Member member, Pageable pageable);

    ResMemoDTO createMemo(Long memberId, ReqMemoUpdateDTO reqMemoUpdateDTO, Member auth);
}
