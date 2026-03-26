package com.example.SwiggyClone.dto.response;

import java.util.List;
import com.example.SwiggyClone.dto.request.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    
    private List<OrderItemDto> orderItems;

    private Double amount;
}
