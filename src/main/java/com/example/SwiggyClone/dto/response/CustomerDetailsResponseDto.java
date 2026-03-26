package com.example.SwiggyClone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailsResponseDto {
    private int points;
    private int ratings;
    private boolean isSubscribed;
}
