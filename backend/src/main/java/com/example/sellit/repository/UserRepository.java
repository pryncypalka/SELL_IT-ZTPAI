package com.example.sellit.repository;

import com.example.sellit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query("SELECT u.userId FROM User u WHERE u.email = :email")
    Long GetUserIdByEmail(String email);

    @Query("SELECT u.freeOffers FROM User u WHERE u.email = :email")
    Integer findUserFreeOffersByEmail(String email);



}
