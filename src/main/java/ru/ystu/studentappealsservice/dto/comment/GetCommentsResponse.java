package ru.ystu.studentappealsservice.dto.comment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentsResponse {

    private List<CommentDTO> comments;
}
