package ru.ystu.studentappealsservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String role;
}
