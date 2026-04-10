package com.example.SwiggyClone.service.application_service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SwiggyClone.dto.request.FoodItemAddRequestDto;
import com.example.SwiggyClone.dto.request.FoodItemUpdateRequestDto;
import com.example.SwiggyClone.dto.response.FoodItemDetailsResponseDto;
import com.example.SwiggyClone.dto.response.FoodItemUpdateResponseDto;
import com.example.SwiggyClone.exception.ResourceNotFoundException;
import com.example.SwiggyClone.model.FoodItems;
import com.example.SwiggyClone.model.Restaurant;
import com.example.SwiggyClone.repository.FoodItemRepository;
import com.example.SwiggyClone.repository.RestaurantRepository;
import com.example.SwiggyClone.service.application_service.FoodItemsService;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class FoodItemsServiceImplementation implements FoodItemsService {

    private FoodItemRepository foodItemRepository;
    private RestaurantRepository restaurantRepository;
    @Transactional
    @Override
    public FoodItemDetailsResponseDto addFoodItem(FoodItemAddRequestDto foodItem)
    {

        Restaurant restaurant=restaurantRepository.findById(foodItem.getRestaurantId()).orElseThrow(()->
            new ResourceNotFoundException("Restuarant Not Found"));
        FoodItems newFoodItem=new FoodItems();
        newFoodItem.setCost(foodItem.getCost());
        newFoodItem.setDescriptions(foodItem.getDescriptions());
        newFoodItem.setFoodCategory(foodItem.getFoodCategory());
        newFoodItem.setFoodName(foodItem.getFoodName());
        newFoodItem.setFoodType(foodItem.getFoodType());
        newFoodItem.setIsAvailable(foodItem.getIsAvailable());
        newFoodItem.setRestaurant(restaurant);
        newFoodItem.setRatings(0);
        newFoodItem.setImageUrl(foodItem.getImageUrl());

        FoodItems saved = foodItemRepository.save(newFoodItem);
        return getFoodItemById(saved.getFoodId());
    }

    public List<FoodItemDetailsResponseDto> getFoodItems(Long restaurantId)
    {
       List<FoodItemDetailsResponseDto>foodItemDetailsResponseDtos =new ArrayList<>();

       List<FoodItems>foodItems=foodItemRepository.getFoodItemByRestaurantId(restaurantId).orElseThrow(()->
            new ResourceNotFoundException("FoodItem Not Found"));

       for(FoodItems item : foodItems){
          FoodItemDetailsResponseDto dto=new FoodItemDetailsResponseDto();
          dto.setFoodId(item.getFoodId());
          dto.setCost(item.getCost());
          dto.setDescriptions(item.getDescriptions());
          dto.setFoodCategory(item.getFoodCategory());
          dto.setFoodName(item.getFoodName());
          dto.setFoodType(item.getFoodType());
          dto.setIsAvailable(item.getIsAvailable());
          dto.setRatings(item.getRatings());
          foodItemDetailsResponseDtos.add(dto);
       }
       return foodItemDetailsResponseDtos;
    }

    public FoodItemDetailsResponseDto getFoodItemById(Long foodId)
    {
        FoodItemDetailsResponseDto dto=new FoodItemDetailsResponseDto();
        FoodItems item=foodItemRepository.findById(foodId).orElseThrow(()->
        new ResourceNotFoundException("FoodItem Not Found"));
        dto.setFoodId(item.getFoodId());
        dto.setCost(item.getCost());
        dto.setDescriptions(item.getDescriptions());
        dto.setFoodCategory(item.getFoodCategory());
        dto.setFoodName(item.getFoodName());
        dto.setFoodType(item.getFoodType());
        dto.setIsAvailable(item.getIsAvailable());
        dto.setRatings(item.getRatings());

        return dto;
    }

    @Transactional
    public FoodItemUpdateResponseDto updateFoodItem(FoodItemUpdateRequestDto foodItemDto)
    {
         FoodItems item=foodItemRepository.findById(foodItemDto.getFoodItemId()).orElseThrow(()->
         new ResourceNotFoundException("FoodItem Not Found"));

         
        if (foodItemDto.getFoodName() != null && !foodItemDto.getFoodName().trim().isEmpty()) {
            item.setFoodName(foodItemDto.getFoodName());
        }

        if (foodItemDto.getCost() != null) {
            item.setCost(foodItemDto.getCost());
        }

        if (foodItemDto.getDescriptions() != null && !foodItemDto.getDescriptions().trim().isEmpty()) {
            item.setDescriptions(foodItemDto.getDescriptions());
        }

        if (foodItemDto.getFoodCategory() != null) {
            item.setFoodCategory(foodItemDto.getFoodCategory());
        }

        if (foodItemDto.getFoodType() != null) {
            item.setFoodType(foodItemDto.getFoodType());
        }

        if (foodItemDto.getIsAvailable() != null) {
            item.setIsAvailable(foodItemDto.getIsAvailable());
        }

        if (foodItemDto.getRatings() != null) {
            item.setRatings(foodItemDto.getRatings());
        }
        FoodItems updatedItem = foodItemRepository.save(item);

        FoodItemUpdateResponseDto dto = new FoodItemUpdateResponseDto();
        dto.setFoodItemId(updatedItem.getFoodId());
        dto.setCost(updatedItem.getCost());
        dto.setDescriptions(updatedItem.getDescriptions());
        dto.setFoodCategory(updatedItem.getFoodCategory());
        dto.setFoodName(updatedItem.getFoodName());
        dto.setFoodType(updatedItem.getFoodType());
        dto.setIsAvailable(updatedItem.getIsAvailable());
        dto.setRatings(updatedItem.getRatings());

        return dto;
    }
    

    public String deleteFoodItem(Long foodId) throws ResourceNotFoundException
    {

         if(foodItemRepository.findById(foodId).isPresent())
            foodItemRepository.deleteById(foodId);
       else
           throw new ResourceNotFoundException("Cart Id is not Found");

        return "Cart Deleted Successfully";
        
    }
}
