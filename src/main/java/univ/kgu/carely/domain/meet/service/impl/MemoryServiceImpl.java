package univ.kgu.carely.domain.meet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.kgu.carely.domain.meet.dto.request.ReqMemoryUpdateDTO;
import univ.kgu.carely.domain.meet.dto.response.ResMemoryDTO;
import univ.kgu.carely.domain.meet.entity.Memory;
import univ.kgu.carely.domain.meet.repository.MemoryRepository;
import univ.kgu.carely.domain.meet.service.MemoryService;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class MemoryServiceImpl implements MemoryService {

    public static final String NOT_EXIST_MEMORY_EXCEPTION_MESSAGE = "존재하지 않는 방명록입니다.";
    private final MemberService memberService;
    private final MemoryRepository memoryRepository;

    @Override
    @Transactional
    public ResMemoryDTO updateMemory(Long memoryId, ReqMemoryUpdateDTO reqMemoryUpdateDTO) {
        Member member = memberService.currentMember();

        Memory memory = memoryRepository.findById(memoryId).orElseThrow(() ->
                new RuntimeException(NOT_EXIST_MEMORY_EXCEPTION_MESSAGE));

        if (!member.getMemberId().equals(memory.getSender().getMemberId())
                && !member.getMemberId().equals(memory.getReceiver().getMemberId())) {

            throw new RuntimeException("본인이 작성할 수 있는 방명록이 아닙니다.");
        }

        if (member.getMemberId().equals(memory.getSender().getMemberId())) {
            memory.setSenderMemo(reqMemoryUpdateDTO.getMemory());
        } else {
            memory.setReceiverMemo(reqMemoryUpdateDTO.getMemory());
        }

        Memory save = memoryRepository.save(memory);

        return toResMemoryDTO(save);
    }

    @Override
    @Transactional(readOnly = true)
    public ResMemoryDTO readMemory(Long memoryId) {
        Member member = memberService.currentMember();
        Memory memory = memoryRepository.findById(memoryId).orElseThrow(() ->
                new RuntimeException(NOT_EXIST_MEMORY_EXCEPTION_MESSAGE));

        if (!member.getMemberId().equals(memory.getSender().getMemberId())
                && !member.getMemberId().equals(memory.getReceiver().getMemberId())) {
            throw new RuntimeException("본인이 열람 가능한 방명록이 아닙니다.");
        }

        return toResMemoryDTO(memory);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResMemoryDTO> readPagedMemory(String query, Pageable pageable) {
        Member member = memberService.currentMember();

        return memoryRepository.findPagedMemoryByNameContaining(query, member,
                pageable);
    }

    @Override
    public ResMemoryDTO toResMemoryDTO(Memory memory) {
        return ResMemoryDTO.builder()
                .memoryId(memory.getId())
                .senderMemo(memory.getSenderMemo())
                .receiverMemo(memory.getReceiverMemo())
                .createdAt(memory.getCreatedAt())
                .updatedAt(memory.getUpdatedAt())
                .sender(memberService.toResMemberSmallInfoDTO(memory.getSender()))
                .receiver(memberService.toResMemberSmallInfoDTO(memory.getReceiver()))
                .build();
    }

}
