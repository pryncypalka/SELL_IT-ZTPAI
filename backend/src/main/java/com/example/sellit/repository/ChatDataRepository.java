package com.example.sellit.repository;

import com.example.sellit.model.ChatData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatDataRepository extends JpaRepository<ChatData, String>{
}
