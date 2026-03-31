package com.example.SwiggyClone.service.application_service;
import com.example.SwiggyClone.dto.request.UserDetailsEditRequestDto;
import com.example.SwiggyClone.dto.response.UserDetailsResponseDto;


public interface UserService {
    
    //User details
    public UserDetailsResponseDto getUserDetails(String email); 

    public UserDetailsResponseDto editUserDetails(UserDetailsEditRequestDto userDetails);
}
