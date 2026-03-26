package com.example.SwiggyClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwiggyClone.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    
}
