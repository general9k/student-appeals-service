package ru.ystu.studentappealsservice.service;

import ru.ystu.studentappealsservice.dto.status.GetStatusesResponse;
import ru.ystu.studentappealsservice.model.Status;

public interface StatusService {

    GetStatusesResponse getStatuses();

    Status getStatus(Integer id);
}
