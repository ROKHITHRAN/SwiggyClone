package com.example.SwiggyClone.service;

import java.util.List;

import com.example.SwiggyClone.dto.request.FoodItemAddRequestDto;
import com.example.SwiggyClone.dto.request.FoodItemUpdateRequestDto;
import com.example.SwiggyClone.dto.response.FoodItemDetailsResponseDto;

public interface FoodItemsService {
    
    public FoodItemAddRequestDto addFoodItem(FoodItemAddRequestDto foodItem);

    public List<FoodItemDetailsResponseDto> getFoodItems(Long restaurantId);

    public FoodItemDetailsResponseDto getFoodItemById(Long foodId);

    public FoodItemUpdateRequestDto updateFoodItem(FoodItemUpdateRequestDto foodItem);

    public Boolean deleteFoodItem(Long foodId);
}
