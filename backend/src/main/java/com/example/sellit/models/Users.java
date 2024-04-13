package com.example.sellit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "password_hashed", nullable = false)
    private String passwordHashed;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    private Timestamp createTime;

    @Column(name = "id_user_details", nullable = false)
    private Integer idUserDetails;

    @Column(name = "email", nullable = false)
    private String email;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
