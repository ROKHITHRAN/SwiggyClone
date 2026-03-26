package com.example.SwiggyClone.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateRequestDto {
    private Long cartId;
    private Long foodItemId;
    private int quantity;
}
