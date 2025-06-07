package univ.kgu.carely.domain.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.kgu.carely.domain.chat.dto.ChatMessageResponse;
import univ.kgu.carely.domain.chat.dto.ChatRoomResponse;
import univ.kgu.carely.domain.chat.repository.ChatRoomRepository;
import univ.kgu.carely.domain.chat.service.ChatMessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatRestController {
    private final ChatMessageService chatMessageService;

    @Operation(
            summary = "채팅 메시지 조회",
            description = "지정된 채팅방 ID를 기반으로 저장된 메시지 목록을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "메시지 조회 성공"),
            @ApiResponse(responseCode = "404", description = "채팅방 또는 메시지를 찾을 수 없음")
    })
    @GetMapping("/{chatRoomId}/messages")
    public ResponseEntity<List<ChatMessageResponse>> getChatMessages(
            @Parameter(description = "조회할 채팅방 ID", example = "1")
            @PathVariable Long chatRoomId) {
        List<ChatMessageResponse> messages = chatMessageService.getChatMessageByChatRoomId(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{memberId}/rooms")
    public ResponseEntity<List<ChatRoomResponse>> getChatRooms(@PathVariable Long memberId) {
        List<ChatRoomResponse> chatRooms = chatMessageService.getChatRoomByMemberId(memberId);
        return ResponseEntity.ok(chatRooms);
    }

    @DeleteMapping("/{chatRoomId}")
    public ResponseEntity<Boolean> deleteChatRoom(@PathVariable Long chatRoomId) {
        Boolean response = chatMessageService.deleteChatRoom(chatRoomId);
        return ResponseEntity.ok(response);
    }
}
