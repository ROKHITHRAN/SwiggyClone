package com.example.SwiggyClone.service.application_service;

import java.util.List;

import com.example.SwiggyClone.dto.request.OrderStatusUpdateRequestDto;
import com.example.SwiggyClone.dto.response.OrderResponseDto;

public interface OrderService {
    
    public OrderResponseDto getCustomerCurrentOrder(Long customerId);
    public OrderResponseDto getRestaurantCurrentOrder(Long restaurantId);
    public OrderResponseDto getDeliveryCurrentOrder(Long deliveryId);

    public List<OrderResponseDto> getCustomerOrderHistory(Long customerId);
    public List<OrderResponseDto> getRestaurantOrderHistory(Long restaurantId);
    public List<OrderResponseDto> getDeliveryOrderHistory(Long deliveryId);

    public OrderStatusUpdateRequestDto updateOrderStatus(OrderStatusUpdateRequestDto orderStatus);
}
