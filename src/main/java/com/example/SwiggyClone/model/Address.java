package com.example.SwiggyClone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String street;

    private String district;

    private String state;

    private String pinCode;

    public Address(String street, String district, String state, String pinCode) {
        this.street = street;
        this.district = district;
        this.state = state;
        this.pinCode = pinCode;
    }

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}
