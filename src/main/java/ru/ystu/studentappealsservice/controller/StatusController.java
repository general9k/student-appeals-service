package ru.ystu.studentappealsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ystu.studentappealsservice.controller.api.StatusControllerApi;
import ru.ystu.studentappealsservice.dto.status.GetStatusesResponse;
import ru.ystu.studentappealsservice.service.StatusService;

@RestController
@RequiredArgsConstructor
public class StatusController implements StatusControllerApi {

    private final StatusService statusService;

    @Override
    public ResponseEntity<GetStatusesResponse> getStatuses() {
        return ResponseEntity.ok(statusService.getStatuses());
    }
}
