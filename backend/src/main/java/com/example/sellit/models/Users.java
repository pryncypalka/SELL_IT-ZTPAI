package com.example.sellit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.Set;

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
    private Long userId;

    @Column(name = "password_hashed", nullable = false)
    private String passwordHashed;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "username")
    private String username;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(name = "usersRole",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Offers> offers;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Templates> templates;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private AllegroIntegrationData allegroIntegrationData;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private OpenAIIntegrationData openAIIntegrationData;


}
