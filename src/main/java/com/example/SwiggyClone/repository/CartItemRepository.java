package com.example.SwiggyClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwiggyClone.model.CartItems;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems,Long> {
    
}
