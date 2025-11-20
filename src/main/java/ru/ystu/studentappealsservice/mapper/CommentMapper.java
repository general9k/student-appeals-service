package ru.ystu.studentappealsservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.ystu.studentappealsservice.dto.comment.CommentDTO;
import ru.ystu.studentappealsservice.dto.comment.CreateCommentDTO;
import ru.ystu.studentappealsservice.dto.comment.GetCommentsResponse;
import ru.ystu.studentappealsservice.model.Comment;
import ru.ystu.studentappealsservice.model.Note;
import ru.ystu.studentappealsservice.model.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class})
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "text", source = "request.text")
    @Mapping(target = "note", source = "note")
    @Mapping(target = "author", source = "author")
    Comment toEntity(CreateCommentDTO request, Note note, User author);

    CommentDTO toDto(Comment comment);

    List<CommentDTO> toDto(List<Comment> comments);

    default GetCommentsResponse toGetCommentsResponse(List<Comment> comments) {
        return new GetCommentsResponse(toDto(comments));
    }
}
