package com.example.SwiggyClone.service;

import com.example.SwiggyClone.dto.request.CartItemAddRequestDto;
import com.example.SwiggyClone.dto.request.CartUpdateRequestDto;
import com.example.SwiggyClone.dto.request.OrderAddRequestDto;
import com.example.SwiggyClone.dto.response.CartDetailsResponseDto;

public interface CartService {
    
    public CartItemAddRequestDto addItemToCart(CartItemAddRequestDto item);

    public CartUpdateRequestDto updateCartItem(CartUpdateRequestDto item);

    public OrderAddRequestDto checkOutCart(OrderAddRequestDto order);
    
    public CartDetailsResponseDto getCartDetails(Long customerId);

    public String deleteCart(Long customerId);
}
