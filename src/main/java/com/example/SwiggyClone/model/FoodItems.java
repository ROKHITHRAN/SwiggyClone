package com.example.SwiggyClone.model;

import com.example.SwiggyClone.enums.FoodType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FoodItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    private String foodName;

    private int ratings;

    private int cost;

    private String descriptions;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;



    
}
