package com.example.SwiggyClone.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantReviewRequestDto {
    private Long restaurantId;
    private int ratings;
}
