package com.example.SwiggyClone.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class Customer {

    @Id
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private User user;

    private boolean isSubscribed;

    private int points;

    private int ratings;

    @OneToMany(mappedBy = "customer")
    private List<Orders>orders;

    @OneToOne(mappedBy="customer",cascade = CascadeType.ALL)
    private Cart cart;

    public Customer(Long userId, boolean isSubscribed, int points, int ratings) {
        this.userId = userId;
        this.isSubscribed = isSubscribed;
        this.points = points;
        this.ratings = ratings;
    }  
}
