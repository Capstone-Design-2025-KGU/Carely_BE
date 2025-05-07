package univ.kgu.carely.domain.meet.service;

import org.springframework.scheduling.annotation.Async;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoSumCreateDTO;

public interface MemoSumService {

    @Async
    void summarization(ReqMemoSumCreateDTO memo);
}
