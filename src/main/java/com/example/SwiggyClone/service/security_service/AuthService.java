package com.example.SwiggyClone.service.security_service;

import com.example.SwiggyClone.dto.request.LoginRequestDto;
import com.example.SwiggyClone.dto.request.RegisterRequestDto;
import com.example.SwiggyClone.dto.response.LoginResponseDto;
import com.example.SwiggyClone.dto.response.UserDetailsResponseDto;


public interface AuthService {
    public UserDetailsResponseDto register(RegisterRequestDto registerRequestDto);
    public LoginResponseDto login(LoginRequestDto loginRequestDto);
}
