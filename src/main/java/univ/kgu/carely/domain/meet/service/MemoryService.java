package univ.kgu.carely.domain.meet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoryUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryDTO;
import univ.kgu.carely.domain.meet.entity.Memory;

public interface MemoryService {

    ResMemoryDTO updateMemory(Long memoryId, ReqMemoryUpdateDTO reqMemoryUpdateDTO);

    ResMemoryDTO readMemory(Long memoryId);

    Page<ResMemoryDTO> readPagedMemory(String query, Pageable pageable);

    ResMemoryDTO toResMemoryDTO(Memory memory);
}
