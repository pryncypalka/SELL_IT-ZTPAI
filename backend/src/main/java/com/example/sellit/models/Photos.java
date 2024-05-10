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
@Table(name = "photos")
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_photo", nullable = false)
    private Long idPhoto;

    @Column(name = "photo_path", nullable = false)
    private String photoPath;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offers offers;
}
