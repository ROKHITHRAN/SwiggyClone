package com.example.SwiggyClone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SwiggyClone.model.FoodItems;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItems,Long>{
    
    @Query("select f from FoodItems f where f.restaurant.userId = :restaurantId")
    Optional<List<FoodItems>>getFoodItemByRestaurantId(@Param("restaurantId") Long restaurantId);
}
