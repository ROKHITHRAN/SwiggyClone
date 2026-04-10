package com.example.SwiggyClone.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddRequestDto {
    
    private Double amount;

    private Long restaurantId;

    private Long customerId;

    private Long paymentId;
}
