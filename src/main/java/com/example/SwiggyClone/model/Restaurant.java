package com.example.SwiggyClone.model;

import java.util.List;

import com.example.SwiggyClone.enums.FoodType;
import com.example.SwiggyClone.enums.RestaurantStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Restaurant {
    @Id
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private User user;

    private String restName;

    private int ratings;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private RestaurantStatus restaurantStatus;

    @Enumerated(EnumType.STRING)
    @Column(name="food_type")
    private FoodType foodType;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<Orders>orders;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<FoodItems>foodItems;

}
