package univ.kgu.carely.domain.meet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoSumCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoSumDTO;
import univ.kgu.carely.domain.meet.service.MemoSumService;

@Service
@RequiredArgsConstructor
public class MemoSumServiceImpl implements MemoSumService {

    private final WebClient webClient;

    @Override
    @Async
    public void summarization(final ReqMemoSumCreateDTO memo) {
        Disposable subscribe = webClient.post()
                .uri("/summarize")
                .bodyValue(memo)
                .retrieve()
                .toEntity(ResMemoSumDTO.class)
                .map(ResponseEntity::getBody)
                .doOnSuccess(resMemoSumDTO -> {
                    System.out.println(resMemoSumDTO);
                })
                .doOnError(Throwable::printStackTrace)
                .subscribe();
    }
}
