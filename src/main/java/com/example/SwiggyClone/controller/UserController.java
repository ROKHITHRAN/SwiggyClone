package com.example.SwiggyClone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiggyClone.dto.request.UserDetailsEditRequestDto;
import com.example.SwiggyClone.dto.response.UserDetailsResponseDto;
import com.example.SwiggyClone.service.application_service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping("/getDetails")
    public UserDetailsResponseDto getUserDetils(@RequestParam String email)
    {
        return userService.getUserDetails(email);
    } 

    @PutMapping("/update")
    public UserDetailsResponseDto editUserDetails(@RequestBody UserDetailsEditRequestDto userDetails)
    {
        return userService.editUserDetails(userDetails);
    }
}
