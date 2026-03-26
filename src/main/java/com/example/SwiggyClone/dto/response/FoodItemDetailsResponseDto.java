package com.example.SwiggyClone.dto.response;

import com.example.SwiggyClone.enums.FoodCategory;
import com.example.SwiggyClone.enums.FoodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDetailsResponseDto {
    private String foodName;

    private int ratings;

    private int cost;

    private String descriptions;

    private FoodType foodType;

    private Boolean isAvailable;

    private FoodCategory foodCategory;

}
