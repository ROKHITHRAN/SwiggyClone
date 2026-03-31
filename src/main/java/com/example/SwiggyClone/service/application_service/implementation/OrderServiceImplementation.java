package com.example.SwiggyClone.service.application_service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SwiggyClone.dto.request.OrderItemDto;
import com.example.SwiggyClone.dto.request.OrderStatusUpdateRequestDto;
import com.example.SwiggyClone.dto.response.OrderResponseDto;
import com.example.SwiggyClone.dto.response.OrderStatusResponseDto;
import com.example.SwiggyClone.enums.OrderStatus;
import com.example.SwiggyClone.exception.ResourceNotFoundException;
import com.example.SwiggyClone.model.OrderItems;
import com.example.SwiggyClone.model.Orders;
import com.example.SwiggyClone.repository.OrderRepository;
import com.example.SwiggyClone.service.application_service.OrderService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImplementation implements OrderService {

    private OrderRepository orderRepository;

    List<OrderStatus> activeStatuses = List.of(
        OrderStatus.PLACED,
        OrderStatus.CONFIRMED,
        OrderStatus.PREPARING,
        OrderStatus.OUT_FOR_DELIVERY
    );
    List<OrderStatus> restActiveStatuses = List.of(
        OrderStatus.PLACED,
        OrderStatus.CONFIRMED,
        OrderStatus.PREPARING
    );
    List<OrderStatus> deliveryActiveStatuses = List.of(
        OrderStatus.CONFIRMED,
        OrderStatus.PREPARING,
        OrderStatus.OUT_FOR_DELIVERY
    );

    
    public OrderResponseDto getCustomerCurrentOrder(Long customerId)
    {
        Orders currentOrder = orderRepository.getActiveCustomerOrder(customerId, activeStatuses).orElseThrow(()->  
        new ResourceNotFoundException("No Active order found"));

        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setAmount(currentOrder.getAmount());
        responseDto.setOrderId(currentOrder.getOrderId());
        
        List<OrderItems> orderItems = currentOrder.getOrderItems();
        List<OrderItemDto> orderItemsDtos = new ArrayList<>();

        for(OrderItems item : orderItems)
        {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setFoodItemId(item.getFoodItems().getFoodId());
            orderItemDto.setQuantity(item.getQuantity());
            orderItemsDtos.add(orderItemDto);
        }
        responseDto.setOrderItems(orderItemsDtos);

        return responseDto;
    }
    
    public OrderResponseDto getRestaurantCurrentOrder(Long restaurantId)
    {
        Orders currentOrder = orderRepository.getActiveCustomerOrder(restaurantId, activeStatuses).orElseThrow(()->  
        new ResourceNotFoundException("No Active order found"));
    
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setAmount(currentOrder.getAmount());
        responseDto.setOrderId(currentOrder.getOrderId());
        
        List<OrderItems> orderItems = currentOrder.getOrderItems();
        List<OrderItemDto> orderItemsDtos = new ArrayList<>();
    
        for(OrderItems item : orderItems)
        {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setFoodItemId(item.getFoodItems().getFoodId());
            orderItemDto.setQuantity(item.getQuantity());
            orderItemsDtos.add(orderItemDto);
        }
        responseDto.setOrderItems(orderItemsDtos);
    
        return responseDto;
        
    }
    public OrderResponseDto getDeliveryCurrentOrder(Long deliveryId)
    {
        Orders currentOrder = orderRepository.getActiveCustomerOrder(deliveryId, restActiveStatuses).orElseThrow(()->  
        new ResourceNotFoundException("No Active order found"));
    
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setAmount(currentOrder.getAmount());
        responseDto.setOrderId(currentOrder.getOrderId());
        
        List<OrderItems> orderItems = currentOrder.getOrderItems();
        List<OrderItemDto> orderItemsDtos = new ArrayList<>();
    
        for(OrderItems item : orderItems)
        {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setFoodItemId(item.getFoodItems().getFoodId());
            orderItemDto.setQuantity(item.getQuantity());
            orderItemsDtos.add(orderItemDto);
        }
        responseDto.setOrderItems(orderItemsDtos);
    
        return responseDto;
    }

    public List<OrderResponseDto> getCustomerOrderHistory(Long customerId)
    {
        List<Orders> orders = orderRepository.getAllCustomerOrder(customerId).orElseThrow(()->  
        new ResourceNotFoundException("No orders found"));
        
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();

        for(Orders order : orders)
        {
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setAmount(order.getAmount());
            orderResponseDto.setOrderId(order.getOrderId());

            List<OrderItems> orderItems = order.getOrderItems();
            List<OrderItemDto> orderItemsDtos = new ArrayList<>();
    
            for(OrderItems item : orderItems)
            {
                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setFoodItemId(item.getFoodItems().getFoodId());
                orderItemDto.setQuantity(item.getQuantity());
                orderItemsDtos.add(orderItemDto);
            }
            orderResponseDto.setOrderItems(orderItemsDtos);
        }
        return orderResponseDtos;
    }
    
    public List<OrderResponseDto> getRestaurantOrderHistory(Long restaurantId)
    {
        List<Orders> orders = orderRepository.getAllRestaurantOrder(restaurantId).orElseThrow(()->  
        new ResourceNotFoundException("No orders found"));
        
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
    
        for(Orders order : orders)
        {
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setAmount(order.getAmount());
            orderResponseDto.setOrderId(order.getOrderId());
    
            List<OrderItems> orderItems = order.getOrderItems();
            List<OrderItemDto> orderItemsDtos = new ArrayList<>();
    
            for(OrderItems item : orderItems)
            {
                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setFoodItemId(item.getFoodItems().getFoodId());
                orderItemDto.setQuantity(item.getQuantity());
                orderItemsDtos.add(orderItemDto);
            }
            orderResponseDto.setOrderItems(orderItemsDtos);
        }
        return orderResponseDtos;
    }
    
    public List<OrderResponseDto> getDeliveryOrderHistory(Long deliveryId)
    {
        List<Orders> orders = orderRepository.getAllRestaurantOrder(deliveryId).orElseThrow(()->  
        new ResourceNotFoundException("No orders found"));
        
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
    
        for(Orders order : orders)
        {
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setAmount(order.getAmount());
            orderResponseDto.setOrderId(order.getOrderId());
    
            List<OrderItems> orderItems = order.getOrderItems();
            List<OrderItemDto> orderItemsDtos = new ArrayList<>();
    
            for(OrderItems item : orderItems)
            {
                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setFoodItemId(item.getFoodItems().getFoodId());
                orderItemDto.setQuantity(item.getQuantity());
                orderItemsDtos.add(orderItemDto);
            }
            orderResponseDto.setOrderItems(orderItemsDtos);
        }
        return orderResponseDtos;
    }

    @Transactional
    public OrderStatusResponseDto updateOrderStatus(OrderStatusUpdateRequestDto orderStatus)
    {
        Orders order = orderRepository.findById(orderStatus.getOrderId()).orElseThrow(()->  
        new ResourceNotFoundException("No orders found"));

        order.setOrderStatus(orderStatus.getOrderStatus());
        Orders updated = orderRepository.save(order);
        OrderStatusResponseDto responseDto = new OrderStatusResponseDto();
        responseDto.setOrderId(updated.getOrderId());
        responseDto.setOrderStatus(updated.getOrderStatus());
        return responseDto;
    }

    public OrderResponseDto getOrderByOrderId(Long orderId)
    {
        Orders currentOrder = orderRepository.findById(orderId).orElseThrow(()->  
        new ResourceNotFoundException("No orders found"));
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setAmount(currentOrder.getAmount());
        responseDto.setOrderId(currentOrder.getOrderId());
        
        List<OrderItems> orderItems = currentOrder.getOrderItems();
        List<OrderItemDto> orderItemsDtos = new ArrayList<>();
    
        for(OrderItems item : orderItems)
        {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setFoodItemId(item.getFoodItems().getFoodId());
            orderItemDto.setQuantity(item.getQuantity());
            orderItemsDtos.add(orderItemDto);
        }
        responseDto.setOrderItems(orderItemsDtos);

        return responseDto;
    }
}
