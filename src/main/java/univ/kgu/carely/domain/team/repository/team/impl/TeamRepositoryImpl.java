package univ.kgu.carely.domain.team.repository.team.impl;

import static univ.kgu.carely.domain.common.embeded.address.QAddress.address;
import static univ.kgu.carely.domain.team.entity.QTeamMate.teamMate;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import univ.kgu.carely.domain.common.embeded.address.Address;
import univ.kgu.carely.domain.team.dto.response.ResTeamOutlineDTO;
import univ.kgu.carely.domain.team.entity.QTeam;
import univ.kgu.carely.domain.team.repository.team.CustomTeamRepository;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements CustomTeamRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QTeam team = QTeam.team;

    @Override
    public Page<ResTeamOutlineDTO> findTeamOutlineWithinDistance(Point point, int meter, Pageable pageable) {
        BooleanTemplate isNearby = Expressions.booleanTemplate(
                "ST_CONTAINS(ST_BUFFER({0}, {1}), {2})", point, meter, team.address.location
        );

        List<ResTeamOutlineDTO> content = jpaQueryFactory.select(Projections.fields(ResTeamOutlineDTO.class,
                        team.teamId,
                        team.teamName,
                        Projections.fields(Address.class,
                                address.province,
                                address.city,
                                address.district,
                                address.details,
                                address.latitude,
                                address.longitude
                                ).as("address"),
                        teamMate.teamMateId.count().as("memberCount")))
                .from(team)
                .leftJoin(teamMate).on(team.eq(teamMate.team))
                .where(isNearby)
                .groupBy(team.teamId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long l = jpaQueryFactory.select(team.teamId.count())
                .from(team)
                .where(isNearby)
                .fetchOne();

        return new PageImpl<>(content, pageable, l.longValue());
    }
}
