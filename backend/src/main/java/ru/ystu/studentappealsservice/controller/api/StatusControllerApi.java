package ru.ystu.studentappealsservice.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ystu.studentappealsservice.controller.api.general.GeneralApi;
import ru.ystu.studentappealsservice.dto.status.GetStatusesResponse;

public interface StatusControllerApi extends GeneralApi {

    @RequestMapping(method = RequestMethod.GET, value = "/statuses")
    ResponseEntity<GetStatusesResponse> getStatuses();
}
