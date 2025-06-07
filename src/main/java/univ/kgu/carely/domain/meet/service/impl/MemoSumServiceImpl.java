package univ.kgu.carely.domain.meet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoSumCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoSumDTO;
import univ.kgu.carely.domain.meet.service.MemoSumService;

@Service
@RequiredArgsConstructor
public class MemoSumServiceImpl implements MemoSumService {

    private final WebClient webClient;

    @Override
    public Mono<ResMemoSumDTO> summarize(final ReqMemoSumCreateDTO dto) {
        return webClient.post()
                .uri("/memo-summary")
                .bodyValue(dto)
                .retrieve()
                .toEntity(ResMemoSumDTO.class)
                .map(ResponseEntity::getBody)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
