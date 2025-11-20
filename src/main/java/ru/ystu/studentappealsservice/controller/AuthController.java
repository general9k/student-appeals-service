package ru.ystu.studentappealsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ystu.studentappealsservice.controller.api.AuthControllerApi;
import ru.ystu.studentappealsservice.dto.jwt.JwtResponse;
import ru.ystu.studentappealsservice.dto.jwt.LoginRequest;
import ru.ystu.studentappealsservice.security.jwt.JwtService;
import ru.ystu.studentappealsservice.service.impl.UserServiceImpl;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userDetailsService;

    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
