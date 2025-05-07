package univ.kgu.carely.domain.meet.service;

import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoSumCreateDTO;

@SpringBootTest
class MemoSumServiceTest {

    @Autowired
    MemoSumService memoSumService;

    @Test
    void test1() throws InterruptedException {
        ReqMemoSumCreateDTO memo = new ReqMemoSumCreateDTO();
        memo.setText("한국인은 김치가 최고입니다. 김치를 먹어야 하는 이유는 1. 맛있어서, 2. "
                + "한국인이니까, 3. 유산균이 많이 있어서, 4. 대부분의 한식에 잘 어울려서"
                + " 일단 한번 먹어보세요.");
        memo.setTopic("김치");

        CompletableFuture<Void> completableFuture = new CompletableFuture().completeAsync(() -> {
            memoSumService.summarization(memo);
            memoSumService.summarization(memo);
            memoSumService.summarization(memo);
            memoSumService.summarization(memo);
            memoSumService.summarization(memo);

            return Void.TYPE;
        });

        completableFuture.join();
    }

}