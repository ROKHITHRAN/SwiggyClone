package com.example.SwiggyClone.service.application_service;

import com.example.SwiggyClone.dto.request.CartItemAddRequestDto;
import com.example.SwiggyClone.dto.request.CartUpdateRequestDto;
import com.example.SwiggyClone.dto.request.OrderAddRequestDto;
import com.example.SwiggyClone.dto.response.CartDetailsResponseDto;
import com.example.SwiggyClone.model.Cart;
import com.example.SwiggyClone.model.Orders;

public interface CartService {
    
    public Cart addItemToCart(CartItemAddRequestDto item);

    public Cart updateCartItem(CartUpdateRequestDto item);

    public Orders checkOutCart(OrderAddRequestDto order);
    
    public CartDetailsResponseDto getCartDetails(Long customerId);

    public String deleteCart(Long customerId);
}
