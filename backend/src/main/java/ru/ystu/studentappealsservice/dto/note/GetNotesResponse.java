package ru.ystu.studentappealsservice.dto.note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ystu.studentappealsservice.dto.PaginationInfo;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetNotesResponse {

    private List<NoteDTO> notes;

    private PaginationInfo paginationInfo;
}
