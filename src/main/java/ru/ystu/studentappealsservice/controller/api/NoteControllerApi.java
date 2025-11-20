package ru.ystu.studentappealsservice.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ystu.studentappealsservice.controller.api.general.GeneralApi;
import ru.ystu.studentappealsservice.dto.note.CreateNoteDTO;
import ru.ystu.studentappealsservice.dto.note.EditNoteStatusDTO;
import ru.ystu.studentappealsservice.dto.note.GetNotesResponse;
import ru.ystu.studentappealsservice.dto.note.NoteDTO;

import java.util.List;

public interface NoteControllerApi extends GeneralApi {

    @RequestMapping(method = RequestMethod.GET, value = "/notes")
    ResponseEntity<GetNotesResponse> getNotes(@RequestParam(value = "noteId", required = false) Integer noteId,
                                              @RequestParam(value = "statusId", required = false) Integer statusId,
                                              @RequestParam(value = "topicId", required = false) Integer topicId,
                                              @RequestParam(value = "searchString", required = false) String searchString,
                                              @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                              @RequestParam(value = "sort", required = false) List<String> sort);

    @RequestMapping(method = RequestMethod.POST, value = "/note")
    ResponseEntity<NoteDTO> createNote(@RequestBody CreateNoteDTO request);

    @RequestMapping(method = RequestMethod.PUT, value = "/note/{id}/status")
    ResponseEntity<NoteDTO> editNoteStatus(@PathVariable("id") Integer noteId, @RequestBody EditNoteStatusDTO request);
}
