package com.example.SwiggyClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwiggyClone.model.OrderItems;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems,Long> {
    
}
