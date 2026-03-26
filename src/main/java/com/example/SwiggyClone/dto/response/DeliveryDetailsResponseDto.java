package com.example.SwiggyClone.dto.response;

import java.sql.Blob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDetailsResponseDto {
    private Blob currentLoc;

    private int points;

    private int ratings;

}
