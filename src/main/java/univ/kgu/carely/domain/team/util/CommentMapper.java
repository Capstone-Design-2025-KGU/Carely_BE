package univ.kgu.carely.domain.team.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import univ.kgu.carely.domain.member.util.MemberMapper;
import univ.kgu.carely.domain.team.dto.response.ResCommentDTO;
import univ.kgu.carely.domain.team.entity.Comment;

@Mapper(componentModel = "spring", uses = MemberMapper.class)
public interface CommentMapper {

    @Mapping(target = "writer", source = "member")
    ResCommentDTO toResCommentDto(Comment comment);

}
