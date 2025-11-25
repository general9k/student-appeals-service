package ru.ystu.studentappealsservice.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ystu.studentappealsservice.controller.api.general.GeneralApi;
import ru.ystu.studentappealsservice.dto.comment.CommentDTO;
import ru.ystu.studentappealsservice.dto.comment.CreateCommentDTO;
import ru.ystu.studentappealsservice.dto.comment.GetCommentsResponse;

public interface CommentControllerApi extends GeneralApi {

    @RequestMapping(method = RequestMethod.GET, value = "/note/{id}/comments")
    ResponseEntity<GetCommentsResponse> getComments(@PathVariable("id") Integer noteId);

    @RequestMapping(method = RequestMethod.POST, value = "/note/{id}/comment")
    ResponseEntity<CommentDTO> createComment(@PathVariable("id") Integer noteId, @RequestBody CreateCommentDTO request);
}
