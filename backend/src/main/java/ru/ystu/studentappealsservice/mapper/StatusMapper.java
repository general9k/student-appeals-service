package ru.ystu.studentappealsservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.ystu.studentappealsservice.dto.status.GetStatusesResponse;
import ru.ystu.studentappealsservice.dto.status.StatusDTO;
import ru.ystu.studentappealsservice.model.Status;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatusMapper {

    StatusDTO toDto(Status status);

    List<StatusDTO> toDto(List<Status> statuses);

    default GetStatusesResponse toResponseDto(List<Status> statuses) {
        return new GetStatusesResponse(toDto(statuses));
    }
}
