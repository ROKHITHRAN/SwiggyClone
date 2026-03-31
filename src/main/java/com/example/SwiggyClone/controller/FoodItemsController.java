package com.example.SwiggyClone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiggyClone.dto.request.FoodItemAddRequestDto;
import com.example.SwiggyClone.dto.request.FoodItemUpdateRequestDto;
import com.example.SwiggyClone.dto.response.FoodItemDetailsResponseDto;
import com.example.SwiggyClone.dto.response.FoodItemUpdateResponseDto;
import com.example.SwiggyClone.service.application_service.FoodItemsService;

@RestController
@RequestMapping("/foodItems")
public class FoodItemsController {
    private FoodItemsService foodItemsService;

    @PostMapping("/add")
    public FoodItemDetailsResponseDto addFoodItem(@RequestBody FoodItemAddRequestDto foodItem)
    {
        return foodItemsService.addFoodItem(foodItem);
    }

    @GetMapping("/getAllByRest/{restaurantId}")
    public List<FoodItemDetailsResponseDto> getFoodItems(@PathVariable Long restaurantId)
    {
        return foodItemsService.getFoodItems(restaurantId);
    }

    @GetMapping("/getById/{foodId}")
    public FoodItemDetailsResponseDto getFoodItemById(@PathVariable Long foodId)
    {
        return foodItemsService.getFoodItemById(foodId);
    }

    @PutMapping("/update")
    public FoodItemUpdateResponseDto updateFoodItem(@RequestBody FoodItemUpdateRequestDto foodItem)
    {
        return foodItemsService.updateFoodItem(foodItem);
    }

    @DeleteMapping("/delete/{foodId}")
    public String deleteFoodItem(@PathVariable Long foodId)
    {
        return foodItemsService.deleteFoodItem(foodId);
    }
}
