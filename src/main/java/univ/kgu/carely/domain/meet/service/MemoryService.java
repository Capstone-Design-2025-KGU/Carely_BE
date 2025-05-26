package univ.kgu.carely.domain.meet.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoryUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryCardDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryDTO;
import univ.kgu.carely.domain.meet.entity.Memory;
import univ.kgu.carely.domain.member.entity.Member;

public interface MemoryService {

    /**
     * 방명록을 수정한다.
     *
     * @param member
     * @param memoryId           수정하려고 하는 방명록 ID
     * @param reqMemoryUpdateDTO 수정하려고 하는 내용
     * @return 수정된 방명록 정보
     */
    ResMemoryDTO updateMemory(Member member, Long memoryId, ReqMemoryUpdateDTO reqMemoryUpdateDTO);

    /**
     * 방명록을 조회한다.
     *
     * @param member
     * @param memoryId 조회하려고 하는 방명록 ID
     * @return 방명록 정보
     */
    ResMemoryDTO readMemory(Member member, Long memoryId);

    /**
     * 본인과 쿼리 이름이 포함된 방명록을 찾는다.
     *
     * @param member
     * @param query    검색하려고 하는 사람 이름
     * @param pageable 페이징 정보
     * @return 해당하는 방명록 리스트
     */
    Page<ResMemoryDTO> readPagedMemory(Member member, String query, Pageable pageable);

    ResMemoryDTO toResMemoryDTO(Memory memory);

    List<ResMemoryCardDTO> getOthersMemories(Long memberId, Member auth);
}
