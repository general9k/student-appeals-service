package ru.ystu.studentappealsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ystu.studentappealsservice.controller.api.NoteControllerApi;
import ru.ystu.studentappealsservice.dto.note.CreateNoteDTO;
import ru.ystu.studentappealsservice.dto.note.EditNoteStatusDTO;
import ru.ystu.studentappealsservice.dto.note.GetNotesResponse;
import ru.ystu.studentappealsservice.dto.note.NoteDTO;
import ru.ystu.studentappealsservice.service.NoteService;
import ru.ystu.studentappealsservice.util.PageUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteController implements NoteControllerApi {

    private final NoteService noteService;

    @Override
    public ResponseEntity<GetNotesResponse> getNotes(Integer noteId, Integer statusId, Integer topicId, String searchString,
                                                     Integer page, Integer size, List<String> sort) {
        return ResponseEntity.ok(noteService.getNotes(noteId, statusId, topicId, searchString, PageUtils.pageable(size, page, sort)));
    }

    @Override
    public ResponseEntity<NoteDTO> createNote(CreateNoteDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(request));
    }

    @Override
    public ResponseEntity<NoteDTO> editNoteStatus(Integer noteId, EditNoteStatusDTO request) {
        return ResponseEntity.ok(noteService.editNoteStatus(noteId, request));
    }
}
