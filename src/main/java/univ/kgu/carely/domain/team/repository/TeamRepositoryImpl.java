package univ.kgu.carely.domain.team.repository;

import static univ.kgu.carely.domain.team.entity.QTeamMate.teamMate;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.team.dto.response.ResTeamOutlineDTO;
import univ.kgu.carely.domain.team.entity.QTeam;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements CustomTeamRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QTeam team = QTeam.team;

    @Override
    public Page<ResTeamOutlineDTO> findTeamOutlineWithinDistance(Member member, int meter, Pageable pageable) {
        NumberExpression<Double> distance = Expressions.numberTemplate(Double.class,
                "ST_Distance_Sphere(POINT({0}, {1}), POINT({2}, {3}))",
                team.address.longitude, team.address.latitude, member.getAddress().getLongitude(),
                member.getAddress().getLatitude());

        List<ResTeamOutlineDTO> content = jpaQueryFactory.select(Projections.fields(ResTeamOutlineDTO.class,
                        team.teamId,
                        team.teamName,
                        team.address,
                        distance.as("distance"),
                        teamMate.count().as("memberCount")))
                .from(team)
                .leftJoin(teamMate).on(team.eq(teamMate.team))
                .where(distance.loe(meter))
                .groupBy(team.teamId)
                .orderBy(pageable.getSort().stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long i = jpaQueryFactory.select(Projections.fields(Long.class,
                        team.count()))
                .from(team)
                .where(distance.loe(meter))
                .fetchOne();

        PageImpl<ResTeamOutlineDTO> page = new PageImpl<>(content, pageable, i);

        return page;
    }
}
