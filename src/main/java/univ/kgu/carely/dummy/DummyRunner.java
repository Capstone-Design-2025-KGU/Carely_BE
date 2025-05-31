package univ.kgu.carely.dummy;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyRunner {

    private final MemberDummy memberDummy;
    private final TeamDummy teamDummy;
    private final TeamMateDummy teamMateDummy;
    private final ChatRoomDummy chatRoomDummy;
    private final ChatMemberDummy chatMemberDummy;
    private final PostDummy postDummy;
    private final CommentDummy commentDummy;
    private final MeetingDummy meetingDummy;
    private final MemoDummy memoDummy;
    private final MemoryDummy memoryDummy;

    @EventListener(ApplicationReadyEvent.class)
    public void runner() {
        try {
            memberDummy.makeMember();
            teamDummy.makeTeam();
            teamMateDummy.makeTeamMate();
            chatRoomDummy.makeChatRoom();
            chatMemberDummy.makeChatMember();
            postDummy.makePost();
            commentDummy.makeComment();
            meetingDummy.makeMeeting();
            memoDummy.makeMemo();
            memoryDummy.makeMemory();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
