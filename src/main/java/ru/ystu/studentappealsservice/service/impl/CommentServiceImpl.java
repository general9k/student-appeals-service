package ru.ystu.studentappealsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ystu.studentappealsservice.dto.comment.CommentDTO;
import ru.ystu.studentappealsservice.dto.comment.CreateCommentDTO;
import ru.ystu.studentappealsservice.dto.comment.GetCommentsResponse;
import ru.ystu.studentappealsservice.mapper.CommentMapper;
import ru.ystu.studentappealsservice.model.Note;
import ru.ystu.studentappealsservice.model.User;
import ru.ystu.studentappealsservice.repository.CommentRepository;
import ru.ystu.studentappealsservice.service.CommentService;
import ru.ystu.studentappealsservice.service.NoteService;
import ru.ystu.studentappealsservice.service.UserService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final NoteService noteService;
    private final UserService userService;

    @Override
    public GetCommentsResponse getComments(Integer noteId) {
        Note note = noteService.getNote(noteId);
        return commentMapper.toGetCommentsResponse(commentRepository.findByNoteOrderByIdAsc(note));
    }

    @Override
    public CommentDTO createComment(Integer noteId, CreateCommentDTO request) {
        Note note = noteService.getNote(noteId);
        User currentUser = userService.getCurrentUser();
        return commentMapper.toDto(commentRepository.save(commentMapper.toEntity(request, note, currentUser)));
    }
}
