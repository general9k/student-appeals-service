package ru.ystu.studentappealsservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.ystu.studentappealsservice.dto.user.GetUsersResponse;
import ru.ystu.studentappealsservice.dto.user.UserDTO;
import ru.ystu.studentappealsservice.model.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDTO toDto(User user);

    List<UserDTO> toDto(List<User> users);

    default GetUsersResponse toGetUsersResponse(List<User> users) {
        return new GetUsersResponse(toDto(users));
    }
}
