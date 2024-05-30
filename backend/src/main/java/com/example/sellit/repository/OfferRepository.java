package com.example.sellit.repository;


import com.example.sellit.model.Offer;
import com.example.sellit.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long>{

    @Query("SELECT t FROM Offer t WHERE t.user.userId = ?1")
    List<Offer> findAllByUserId(Long userId);
}
