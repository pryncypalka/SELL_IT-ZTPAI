package com.example.sellit.repository;

import com.example.sellit.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    @Query("SELECT t FROM Template t WHERE t.user.userId = ?1")
    List<Template> findAllByUserId(Long userId);
}