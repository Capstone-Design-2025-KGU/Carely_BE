package univ.kgu.carely.domain.team.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import univ.kgu.carely.domain.member.entity.Member;
import univ.kgu.carely.domain.member.util.MemberMapper;
import univ.kgu.carely.domain.team.dto.request.ReqCreatePostDTO;
import univ.kgu.carely.domain.team.dto.request.ReqUpdatePostDTO;
import univ.kgu.carely.domain.team.dto.response.ResPostDTO;
import univ.kgu.carely.domain.team.entity.Post;
import univ.kgu.carely.domain.team.entity.Team;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, CommentMapper.class})
public interface PostMapper {

    @Mapping(target = "member", source = "writer")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "team", source = "team")
    Post toEntity(ReqCreatePostDTO dto, Member writer, Team team);

    @Mapping(target = "resCommentDTOS", source = "comments")
    @Mapping(target = "writer", source = "member")
    ResPostDTO toResPostDto(Post post);

    Post updateEntity(@MappingTarget Post post, ReqUpdatePostDTO dto);

}
