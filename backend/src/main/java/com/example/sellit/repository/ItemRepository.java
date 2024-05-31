package com.example.sellit.repository;

import com.example.sellit.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i JOIN i.subcategory s WHERE s.category.categoryId = :categoryId")
    List<Item> findItemsByCategoryId(@Param("categoryId") Long categoryId);
}