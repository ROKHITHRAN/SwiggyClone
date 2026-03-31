package com.example.SwiggyClone.dto.request;

import java.sql.Blob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestDto {
     private Blob currentLoc;

    private Integer points;

    private Integer ratings;

}
