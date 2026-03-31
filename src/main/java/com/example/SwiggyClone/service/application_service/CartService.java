package com.example.SwiggyClone.service.application_service;

import com.example.SwiggyClone.dto.request.CartItemAddRequestDto;
import com.example.SwiggyClone.dto.request.CartUpdateRequestDto;
import com.example.SwiggyClone.dto.request.OrderAddRequestDto;
import com.example.SwiggyClone.dto.response.CartDetailsResponseDto;
import com.example.SwiggyClone.dto.response.OrderResponseDto;

public interface CartService {
    
    public CartDetailsResponseDto addItemToCart(CartItemAddRequestDto item);

    public CartDetailsResponseDto updateCartItem(CartUpdateRequestDto item);

    public OrderResponseDto checkOutCart(OrderAddRequestDto order);
    
    public CartDetailsResponseDto getCartDetails(Long customerId);

    public String deleteCart(Long customerId);
}
