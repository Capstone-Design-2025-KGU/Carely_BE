package univ.kgu.carely.domain.meet.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoSumCreateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoSumDTO;
import univ.kgu.carely.domain.meet.service.MemoSumService;

@SpringBootTest
class MemoSumServiceImplTest {

    @Autowired
    MemoSumService memoSumService;

    @Test
    void summarize() {
        ReqMemoSumCreateDTO dto = new ReqMemoSumCreateDTO();
        dto.setNotes(
                "환자는 17시에 밥을 반정도 먹다가 남겼다. 밥을 먹고 1시간정도 주무셨다. 이후 밖으로 산책을 나가 10분정도 걷더니 많이 힘들어 하셔 그 자리에서 앉아서 휴식하였다. "
                        + "약은 밥을 먹고 10분 뒤에 드렸고, 충분히 많은 물과 함께 약을 삼켰다. 화장실을 자주 가신다. 대화를 할 때는 자주 화를 내신다. 기분이 들쭉날쭉한 것 같다.");
        ResMemoSumDTO block = memoSumService.summarize(dto).block();
        System.out.println(block);
    }
}