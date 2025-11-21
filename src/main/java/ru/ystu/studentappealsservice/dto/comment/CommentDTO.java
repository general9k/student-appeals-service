package ru.ystu.studentappealsservice.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ystu.studentappealsservice.dto.user.UserDTO;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Integer id;

    private String text;

    private UserDTO author;

    private OffsetDateTime createdAt;
}
