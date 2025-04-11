package univ.kgu.carely.domain.team.repository.team.impl;

import static univ.kgu.carely.domain.team.entity.QTeamMate.teamMate;

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
import univ.kgu.carely.domain.team.repository.team.CustomTeamRepository;

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
                        teamMate.teamMateId.count().as("memberCount")))
                .from(team)
                .leftJoin(teamMate).on(team.eq(teamMate.team))
                .where(distance.loe(meter))
                .groupBy(team.teamId)
                .orderBy(distance.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long l = jpaQueryFactory.select(team.teamId.count())
                .from(team)
                .where(distance.loe(meter))
                .fetchOne();

        return new PageImpl<>(content, pageable, l.longValue());
    }
}
