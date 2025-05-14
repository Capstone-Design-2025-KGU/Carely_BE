package univ.kgu.carely.domain.meet.service;

import reactor.core.publisher.Mono;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoSumCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoSumDTO;

public interface MemoSumService {

    Mono<ResMemoSumDTO> summarize(final ReqMemoSumCreateDTO dto);
}
