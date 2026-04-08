package com.example.SwiggyClone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SwiggyClone.enums.OrderStatus;
import com.example.SwiggyClone.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

        @Query("select o from Orders o where o.customer.userId = :customerId and o.orderStatus in :orderStatus")
        public Optional<Orders> getActiveCustomerOrder(@Param("customerId") Long customerId, @Param("orderStatus") List<OrderStatus> orderStaus);

        @Query("select o from Orders o where o.restaurant.userId = :restaurantId and o.orderStatus in :orderStatus")
        public Optional<Orders> getActiveRestaurantOrder(@Param("restaurantId") Long restaurantId, @Param("orderStatus") List<OrderStatus> orderStaus);
        
        @Query("select o from Orders o where o.delivery.userId = :deliveryId and o.orderStatus in :orderStatus")
        public Optional<Orders> getActiveDeliveryOrder(@Param("deliveryId") Long deliveryId, @Param("orderStatus") List<OrderStatus> orderStaus);
        
        
        @Query("select o from Orders o where o.customer.userId = :customerId")
        public Optional<List<Orders>> getAllCustomerOrder(@Param("customerId") Long customerId);

        @Query("select o from Orders o where o.restaurant.userId = :restaurantId")
        public Optional<List<Orders>> getAllRestaurantOrder(@Param("restaurantId") Long restaurantId);

        @Query("select o from Orders o where o.delivery.userId = :deliveryId")
        public Optional<List<Orders>> getAllDeliveryOrder(@Param("deliveryId") Long deliveryId);
}
