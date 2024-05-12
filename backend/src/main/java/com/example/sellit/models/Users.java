package com.example.sellit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users  implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "username")
    private String username;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Offers> offers;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Templates> templates;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private AllegroIntegrationData allegroIntegrationData;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private OpenAIIntegrationData openAIIntegrationData;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
}
