package com.example.SwiggyClone.dto.response;

import com.example.SwiggyClone.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusResponseDto {
    private Long orderId;
    private OrderStatus orderStatus;
}
