package univ.kgu.carely.domain.meet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import univ.kgu.carely.domain.meet.service.MemoSumService;

@Service
@RequiredArgsConstructor
public class MemoSumServiceImpl implements MemoSumService {

    private final WebClient webClient;

    @Override
    public String summarization(final String memo) {
        return webClient.post()
                .uri("/api/summarization")
                .bodyValue(memo)
                .retrieve()
                .toEntity(String.class)
                .block()
                .getBody();
    }

}
