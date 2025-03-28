package univ.kgu.carely.domain.team.repository;

import static univ.kgu.carely.domain.member.entity.QMember.member;
import static univ.kgu.carely.domain.team.entity.QComment.comment;
import static univ.kgu.carely.domain.team.entity.QTeam.team;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.member.dto.response.ResMemberSmallInfoDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostOutlineDTO;
import univ.kgu.carely.domain.team.entity.QPost;
import univ.kgu.carely.domain.team.entity.Team;

@RequiredArgsConstructor
public class PostRepositoryImpl implements CustomPostRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public static final QPost post = QPost.post;

    @Override
    public Page<ResPostOutlineDTO> findPostOutlineBy(String query, Team searchTeam, Pageable pageable) {
        List<ResPostOutlineDTO> posts = jpaQueryFactory.select(Projections.fields(ResPostOutlineDTO.class,
                        post.postId,
                        post.title,
                        post.createdAt,
                        comment.count().as("commentCount"),
                        Projections.fields(ResMemberSmallInfoDTO.class,
                                member.memberId,
                                member.username,
                                member.name,
                                member.memberType,
                                member.profileImage).as("writer")))
                .from(post)
                .innerJoin(post.member, member)
                .innerJoin(post.comments, comment)
                .where(post.team.teamId.eq(searchTeam.getTeamId())
                        .and(post.title.contains(query)
                                .or(post.content.contains(query))))
                .orderBy(post.postId.desc())
                .groupBy(post.postId)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        Long l = jpaQueryFactory.select(post.count())
                .from(post)
                .where(post.title.contains(query)
                        .or(post.content.contains(query)))
                .fetchOne();

        return new PageImpl<ResPostOutlineDTO>(posts, pageable, l.longValue());
    }
}
