package com.example.SwiggyClone.dto.request;

import com.example.SwiggyClone.enums.FoodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemUpdateRequestDto {
    private Long foodItemId;
    
    private String foodName;

    private int ratings;

    private int cost;

    private String descriptions;

    private FoodType foodType;

    private Boolean isAvailable;

    private String imageUrl;
}
