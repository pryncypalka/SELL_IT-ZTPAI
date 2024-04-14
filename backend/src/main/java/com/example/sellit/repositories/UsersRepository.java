package com.example.sellit.repositories;

import com.example.sellit.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer>{
}
