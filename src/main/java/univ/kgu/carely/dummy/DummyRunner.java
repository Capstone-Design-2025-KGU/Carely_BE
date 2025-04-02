package univ.kgu.carely.dummy;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
@RequiredArgsConstructor
public class DummyRunner {

    private final MemberDummy memberDummy;
    private final TeamDummy teamDummy;
    private final TeamMateDummy teamMateDummy;
    private final ChatRoomDummy chatRoomDummy;
    private final ChatMemberDummy chatMemberDummy;
    private final PostDummy postDummy;
    private final CommentDummy commentDummy;

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            memberDummy.makeMember();
            teamDummy.makeTeam();
            teamMateDummy.makeTeamMate();
            chatRoomDummy.makeChatRoom();
            chatMemberDummy.makeChatMember();
            postDummy.makePost();
            commentDummy.makeComment();
        };
    }

}
