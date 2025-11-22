package ru.ystu.studentappealsservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.ystu.studentappealsservice.dto.user.GetUsersResponse;
import ru.ystu.studentappealsservice.dto.user.UserDTO;
import ru.ystu.studentappealsservice.model.User;

public interface UserService {

    User getCurrentUser();

    UserDTO getCurrentUserDTO();

    boolean isAdmin();

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    GetUsersResponse getUsers();
}
