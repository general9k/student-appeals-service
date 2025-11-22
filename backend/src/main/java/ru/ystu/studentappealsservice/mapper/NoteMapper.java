package ru.ystu.studentappealsservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.ystu.studentappealsservice.dto.note.CreateNoteDTO;
import ru.ystu.studentappealsservice.dto.note.GetNotesResponse;
import ru.ystu.studentappealsservice.dto.note.NoteDTO;
import ru.ystu.studentappealsservice.model.Note;
import ru.ystu.studentappealsservice.model.Status;
import ru.ystu.studentappealsservice.model.Topic;
import ru.ystu.studentappealsservice.model.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StatusMapper.class, TopicMapper.class, UserMapper.class})
public interface NoteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "description", source = "request.description")
    Note toEntity(CreateNoteDTO request, Topic topic, Status status, User author);

    NoteDTO toDto(Note note);

    @Mapping(target = "paginationInfo.page", source = "page")
    @Mapping(target = "paginationInfo.size", source = "size")
    @Mapping(target = "paginationInfo.totalCount", source = "totalCount")
    GetNotesResponse toGetNotesResponse(List<Note> notes, Integer page, Integer size, Long totalCount);
}
