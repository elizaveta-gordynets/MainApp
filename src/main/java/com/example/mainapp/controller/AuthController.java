package com.example.mainapp.controller;

import com.example.mainapp.dto.AuthRequest;
import com.example.mainapp.dto.AuthResponseDto;
import com.example.mainapp.dto.CustomUserDetails;
import com.example.mainapp.service.JwtService;
import com.example.mainapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    public AuthController (UserService userService,
                           JwtService jwtUtil,
                           AuthenticationManager manager) {
        this.userService = userService;
        this.jwtService = jwtUtil;
        this.manager = manager;
    }

    @PostMapping("/user/auth")
    public AuthResponseDto login (@RequestBody @Valid AuthRequest request) {
        try{
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        final CustomUserDetails user = userService.auth(request);
        return new AuthResponseDto(user.getUsername(), jwtService.generateAccessToken(user));
    }
/*
    @PostMapping("/user/auth")
    public void auth(@RequestBody UserInfo user) {
        userService.auth(user);
    }*/

   /* @GetMapping("/user/auth")
    public String getAuthPAge() {
        return "/login";
    }

*/

}
