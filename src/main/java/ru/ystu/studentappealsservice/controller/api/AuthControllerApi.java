package ru.ystu.studentappealsservice.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ystu.studentappealsservice.controller.api.general.GeneralApi;
import ru.ystu.studentappealsservice.dto.jwt.JwtResponse;
import ru.ystu.studentappealsservice.dto.jwt.LoginRequest;

public interface AuthControllerApi extends GeneralApi {

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request);

}
