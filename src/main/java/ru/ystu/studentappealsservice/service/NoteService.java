package ru.ystu.studentappealsservice.service;

import org.springframework.data.domain.Pageable;
import ru.ystu.studentappealsservice.dto.note.CreateNoteDTO;
import ru.ystu.studentappealsservice.dto.note.EditNoteStatusDTO;
import ru.ystu.studentappealsservice.dto.note.GetNotesResponse;
import ru.ystu.studentappealsservice.dto.note.NoteDTO;
import ru.ystu.studentappealsservice.model.Note;

public interface NoteService {

    GetNotesResponse getNotes(Integer noteId, Integer statusId, Integer topicId, String searchString, Pageable pageable);

    NoteDTO createNote(CreateNoteDTO request);

    NoteDTO editNoteStatus(Integer noteId, EditNoteStatusDTO request);

    Note getNote(Integer noteId);
}
