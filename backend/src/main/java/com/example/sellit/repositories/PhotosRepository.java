package com.example.sellit.repositories;

import com.example.sellit.models.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<Photos, Integer> {
}