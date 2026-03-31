package com.example.SwiggyClone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiggyClone.dto.request.OrderStatusUpdateRequestDto;
import com.example.SwiggyClone.dto.response.OrderResponseDto;
import com.example.SwiggyClone.dto.response.OrderStatusResponseDto;
import com.example.SwiggyClone.service.application_service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderservice;

    @GetMapping("/customer/currentOrder/{customerId}")
    public OrderResponseDto getCustomerCurrentOrder(@PathVariable Long customerId){
        return orderservice.getCustomerCurrentOrder(customerId);
    }
    
    @GetMapping("/restaurant/currentOrder/{restaurantId}")
    public OrderResponseDto getRestaurantCurrentOrder(@PathVariable Long restaurantId){
        return orderservice.getRestaurantCurrentOrder(restaurantId);
    }

    @GetMapping("/delivery/currentOrder/{deliveryId}")
    public OrderResponseDto getDeliveryCurrentOrder(@PathVariable Long deliveryId){
        return orderservice.getDeliveryCurrentOrder(deliveryId);
    }

    @GetMapping("/customer/orderHistory/{customerId}")
    public List<OrderResponseDto> getCustomerOrderHistory(@PathVariable Long customerId){
         return orderservice.getCustomerOrderHistory(customerId);
    }
    @GetMapping("/restaurant/orderHistory/{restaurantId}")
    public List<OrderResponseDto> getRestaurantOrderHistory(@PathVariable Long restaurantId){
         return orderservice.getRestaurantOrderHistory(restaurantId);
    }

    @GetMapping("/delivery/orderHistory/{customerId}")
    public List<OrderResponseDto> getDeliveryOrderHistory(@PathVariable Long deliveryId){
         return orderservice.getDeliveryOrderHistory(deliveryId);
    }
    
    @PutMapping("/updateStatus")
    public OrderStatusResponseDto updateOrderStatus(@RequestBody OrderStatusUpdateRequestDto orderStatus){
        return orderservice.updateOrderStatus(orderStatus);
    }
    
    @GetMapping("/getOrder/{orderId}")
    public OrderResponseDto getOrderByOrderId(Long orderId){
       return orderservice.getOrderByOrderId(orderId);
    }
}
