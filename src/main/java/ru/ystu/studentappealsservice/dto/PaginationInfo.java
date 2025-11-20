package ru.ystu.studentappealsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationInfo {

    private Integer page;

    private Integer size;

    private Long totalCount;
}