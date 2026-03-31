package com.example.SwiggyClone.service.application_service;

import java.util.List;

import com.example.SwiggyClone.dto.request.FoodItemAddRequestDto;
import com.example.SwiggyClone.dto.request.FoodItemUpdateRequestDto;
import com.example.SwiggyClone.dto.response.FoodItemDetailsResponseDto;
import com.example.SwiggyClone.dto.response.FoodItemUpdateResponseDto;
import com.example.SwiggyClone.model.FoodItems;

public interface FoodItemsService {
    
    public FoodItemDetailsResponseDto addFoodItem(FoodItemAddRequestDto foodItem);

    public List<FoodItemDetailsResponseDto> getFoodItems(Long restaurantId);

    public FoodItemDetailsResponseDto getFoodItemById(Long foodId);

    public FoodItemUpdateResponseDto updateFoodItem(FoodItemUpdateRequestDto foodItem);

    public String deleteFoodItem(Long foodId);
}
