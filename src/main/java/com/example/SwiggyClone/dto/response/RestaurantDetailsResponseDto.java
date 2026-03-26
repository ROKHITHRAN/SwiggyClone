package com.example.SwiggyClone.dto.response;

import com.example.SwiggyClone.enums.FoodType;
import com.example.SwiggyClone.enums.RestaurantStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDetailsResponseDto {
    private String restaurantName;

    private int ratings;

    private String description;

    private RestaurantStatus restaurantStatus;

    private FoodType foodType;
}
