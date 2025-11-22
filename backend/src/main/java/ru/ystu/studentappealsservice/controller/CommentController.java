package ru.ystu.studentappealsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ystu.studentappealsservice.controller.api.CommentControllerApi;
import ru.ystu.studentappealsservice.dto.comment.CommentDTO;
import ru.ystu.studentappealsservice.dto.comment.CreateCommentDTO;
import ru.ystu.studentappealsservice.dto.comment.GetCommentsResponse;
import ru.ystu.studentappealsservice.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController implements CommentControllerApi {

    private final CommentService commentService;

    @Override
    public ResponseEntity<GetCommentsResponse> getComments(Integer noteId) {
        return ResponseEntity.ok(commentService.getComments(noteId));
    }

    @Override
    public ResponseEntity<CommentDTO> createComment(Integer noteId, CreateCommentDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(noteId, request));
    }
}
