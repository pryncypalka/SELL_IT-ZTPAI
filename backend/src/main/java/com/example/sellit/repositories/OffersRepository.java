package com.example.sellit.repositories;


import com.example.sellit.models.Offers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffersRepository extends JpaRepository<Offers, Integer>{
}