package com.example.SwiggyClone.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    private Integer points;
    private Integer ratings;
    private Boolean isSubscribed;
}
