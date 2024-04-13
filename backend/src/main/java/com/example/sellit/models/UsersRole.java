package com.example.sellit.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_role")
public class UsersRole {

    @Id
    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @Column(name = "id_role", nullable = false)
    private Integer idRole;
}
