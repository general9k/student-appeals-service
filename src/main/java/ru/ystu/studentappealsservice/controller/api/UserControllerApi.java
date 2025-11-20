package ru.ystu.studentappealsservice.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ystu.studentappealsservice.controller.api.general.GeneralApi;
import ru.ystu.studentappealsservice.dto.user.GetUsersResponse;
import ru.ystu.studentappealsservice.dto.user.UserDTO;

public interface UserControllerApi extends GeneralApi {

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    ResponseEntity<GetUsersResponse> getUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/current_user")
    ResponseEntity<UserDTO> getCurrentUser();

}
