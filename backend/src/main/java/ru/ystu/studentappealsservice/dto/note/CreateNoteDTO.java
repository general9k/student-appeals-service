package ru.ystu.studentappealsservice.dto.note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNoteDTO {

    private String name;

    private String description;

    private Integer topicId;

    private Integer statusId;

}
