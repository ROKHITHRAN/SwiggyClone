package com.example.SwiggyClone.service;

import com.example.SwiggyClone.dto.response.UserDetailsResponseDto;

public interface UserService {
    
    //User details
    public UserDetailsResponseDto getUserDetils(Long userId); 

    public UserDetailsResponseDto editUserDetails(Long userId);
}
