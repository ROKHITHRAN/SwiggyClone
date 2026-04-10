package com.example.SwiggyClone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiggyClone.dto.request.LoginRequestDto;
import com.example.SwiggyClone.dto.request.RegisterRequestDto;
import com.example.SwiggyClone.dto.response.LoginResponseDto;
import com.example.SwiggyClone.dto.response.UserDetailsResponseDto;

import com.example.SwiggyClone.service.security_service.AuthService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    
    @PostMapping("/register")
    public UserDetailsResponseDto register(@RequestBody RegisterRequestDto registerRequestDto)
    {
         return authService.register(registerRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto);
    }
    
}
