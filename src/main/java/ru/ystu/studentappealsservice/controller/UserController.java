package ru.ystu.studentappealsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ystu.studentappealsservice.controller.api.UserControllerApi;
import ru.ystu.studentappealsservice.dto.user.GetUsersResponse;
import ru.ystu.studentappealsservice.dto.user.UserDTO;
import ru.ystu.studentappealsservice.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerApi {

    private final UserService userService;

    @Override
    public ResponseEntity<GetUsersResponse> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUserDTO());
    }
}
