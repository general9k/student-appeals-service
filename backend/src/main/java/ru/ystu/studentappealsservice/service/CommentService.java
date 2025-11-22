package ru.ystu.studentappealsservice.service;

import ru.ystu.studentappealsservice.dto.comment.CommentDTO;
import ru.ystu.studentappealsservice.dto.comment.CreateCommentDTO;
import ru.ystu.studentappealsservice.dto.comment.GetCommentsResponse;

public interface CommentService {

    GetCommentsResponse getComments(Integer noteId);

    CommentDTO createComment(Integer noteId, CreateCommentDTO request);

}
