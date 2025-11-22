package ru.ystu.studentappealsservice.service.impl;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ystu.studentappealsservice.dto.note.CreateNoteDTO;
import ru.ystu.studentappealsservice.dto.note.EditNoteStatusDTO;
import ru.ystu.studentappealsservice.dto.note.GetNotesResponse;
import ru.ystu.studentappealsservice.dto.note.NoteDTO;
import ru.ystu.studentappealsservice.exception.ServerLogicException;
import ru.ystu.studentappealsservice.exception.ServerLogicExceptionType;
import ru.ystu.studentappealsservice.mapper.NoteMapper;
import ru.ystu.studentappealsservice.model.Note;
import ru.ystu.studentappealsservice.model.QNote;
import ru.ystu.studentappealsservice.model.Status;
import ru.ystu.studentappealsservice.model.Topic;
import ru.ystu.studentappealsservice.model.User;
import ru.ystu.studentappealsservice.repository.NoteRepository;
import ru.ystu.studentappealsservice.service.NoteService;
import ru.ystu.studentappealsservice.service.StatusService;
import ru.ystu.studentappealsservice.service.TopicService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final UserServiceImpl userService;
    private final TopicService topicService;
    private final StatusService statusService;
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    @Transactional(readOnly = true)
    public GetNotesResponse getNotes(Integer noteId, Integer statusId, Integer topicId, String searchString, Pageable pageable) {
        User currentUser = userService.getCurrentUser();

        QNote note = QNote.note;
        BooleanBuilder predicate = new BooleanBuilder();
        if (Boolean.FALSE.equals(userService.isAdmin())) {
            predicate.and(note.author.eq(currentUser));
        }
        if (StringUtils.isNotEmpty(searchString)) {
            predicate.and(note.name.contains(searchString));
        }
        if (noteId != null) {
            predicate.and(note.id.eq(noteId));
        }
        if (statusId != null) {
            predicate.and(note.status.id.eq(statusId));
        }
        if (topicId != null) {
            predicate.and(note.topic.id.eq(topicId));
        }
        Page<Note> notePage = noteRepository.findAll(predicate, pageable);
        List<Note> notes = notePage.getContent();
        return noteMapper.toGetNotesResponse(notes, pageable.getPageNumber(), pageable.getPageSize(), notePage.getTotalElements());
    }

    @Override
    @Transactional
    public NoteDTO createNote(CreateNoteDTO request) {
        User currentUser = userService.getCurrentUser();
        if (StringUtils.isBlank(request.getName()) || StringUtils.isBlank(request.getDescription())) {
            throw new ServerLogicException("Поля name и description - не могут быть пустыми", ServerLogicExceptionType.VALIDATION_ERROR);
        }
        Topic topic = topicService.getTopic(request.getTopicId());
        Status status = statusService.getStatus(request.getStatusId());
        return noteMapper.toDto(noteRepository.save(noteMapper.toEntity(request, topic, status, currentUser)));
    }

    @Override
    @Transactional
    public NoteDTO editNoteStatus(Integer noteId, EditNoteStatusDTO request) {
        Note note = getNote(noteId);
        Status status = statusService.getStatus(request.getStatusId());
        note.setStatus(status);
        return noteMapper.toDto(note);
    }

    @Override
    public Note getNote(Integer noteId) {
        return noteRepository.findById(noteId).orElseThrow(() ->
                new ServerLogicException("Обращения с id = %s - не найдено".formatted(noteId), ServerLogicExceptionType.NOT_FOUND));
    }
}
