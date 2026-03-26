package com.example.SwiggyClone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwiggyClone.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
}
