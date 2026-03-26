package com.example.SwiggyClone.dto.response;

import com.example.SwiggyClone.enums.Role;
import com.example.SwiggyClone.model.Address;
import com.example.SwiggyClone.model.Images;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsResponseDto {

    // Common User fields
    private String name;
    private String email;
    private String phoneNumber;
    private Address address;
    private Images images;

    // Role-specific nested DTOs
    private CustomerDetailsResponseDto customerDetails;
    private DeliveryDetailsResponseDto deliveryPartnerDetails;
    private RestaurantDetailsResponseDto restaurantDetails;
}