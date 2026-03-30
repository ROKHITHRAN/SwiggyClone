package com.example.SwiggyClone.dto.request;

import com.example.SwiggyClone.model.FoodItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    
    private Long foodItemId;
    private int quantity;
}
