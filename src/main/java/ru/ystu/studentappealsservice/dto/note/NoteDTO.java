package ru.ystu.studentappealsservice.dto.note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ystu.studentappealsservice.dto.status.StatusDTO;
import ru.ystu.studentappealsservice.dto.topic.TopicDTO;
import ru.ystu.studentappealsservice.dto.user.UserDTO;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    private Integer id;

    private String name;

    private String description;

    private OffsetDateTime createdAt;

    private TopicDTO topic;

    private StatusDTO status;

    private UserDTO author;
}
