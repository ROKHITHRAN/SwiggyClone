package com.example.SwiggyClone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiggyClone.dto.request.CartItemAddRequestDto;
import com.example.SwiggyClone.dto.request.CartUpdateRequestDto;
import com.example.SwiggyClone.dto.request.OrderAddRequestDto;
import com.example.SwiggyClone.dto.response.CartDetailsResponseDto;
import com.example.SwiggyClone.dto.response.OrderResponseDto;
import com.example.SwiggyClone.service.application_service.CartService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @PostMapping("/addItem")
    public CartDetailsResponseDto addItemToCart(@RequestBody CartItemAddRequestDto cartItemAddRequestDto) {
        
        return cartService.addItemToCart(cartItemAddRequestDto);
    }
    
    @PutMapping("/updateItem")
    public CartDetailsResponseDto updateCartItem(@RequestBody CartUpdateRequestDto item)
    {
        return cartService.updateCartItem(item);
    }
    

    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody OrderAddRequestDto order){
        return cartService.checkOutCart(order);
    }
     
    @GetMapping("/cartDetails/{customerId}")
    public CartDetailsResponseDto getCartDetails(@PathVariable  Long customerId){
        return cartService.getCartDetails(customerId);
    }
    
    @DeleteMapping("/deleteCart/{customerId}")
    public String deleteCart(@PathVariable Long customerId){
        return cartService.deleteCart(customerId);
    }
}
