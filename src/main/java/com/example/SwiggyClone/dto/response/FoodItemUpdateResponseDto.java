package com.example.SwiggyClone.dto.response;


import com.example.SwiggyClone.enums.FoodCategory;
import com.example.SwiggyClone.enums.FoodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemUpdateResponseDto {
    private Long foodItemId;
    
    private String foodName;

    private Integer ratings;

    private  Integer cost;

    private String descriptions;

    private FoodType foodType;

    private Boolean isAvailable;

    private String imageUrl;

    private FoodCategory foodCategory;
}
