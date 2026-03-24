package com.example.SwiggyClone.model;

import java.sql.Blob;
import java.util.List;

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
public class Delivery {
    @Id
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private User user;

    private Blob currentLoc;

    private int points;

    private int ratings;

    @OneToMany(mappedBy="delivery")
    private List<Orders>orders;

    public Delivery(Long userId, Blob currentLoc, int points, int ratings) {
        this.userId = userId;
        this.currentLoc = currentLoc;
        this.points = points;
        this.ratings = ratings;
    }

}
