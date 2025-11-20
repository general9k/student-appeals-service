package ru.ystu.studentappealsservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ystu.studentappealsservice.dto.user.GetUsersResponse;
import ru.ystu.studentappealsservice.dto.user.UserDTO;
import ru.ystu.studentappealsservice.mapper.UserMapper;
import ru.ystu.studentappealsservice.model.User;
import ru.ystu.studentappealsservice.repository.UserRepository;
import ru.ystu.studentappealsservice.security.details.AuthUserDetails;
import ru.ystu.studentappealsservice.service.UserService;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

    @Override
    public UserDTO getCurrentUserDTO() {
        return userMapper.toDto(getCurrentUser());
    }

    @Override
    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Попытка загрузки пользователя с логином: {}", username);
        return userRepository.findByUsername(username)
                .map(AuthUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Авторизированный пользователь с таким %s не найден", username)));
    }

    @Override
    @Transactional(readOnly = true)
    public GetUsersResponse getUsers() {
        return userMapper.toGetUsersResponse(userRepository.findAll());
    }

}
