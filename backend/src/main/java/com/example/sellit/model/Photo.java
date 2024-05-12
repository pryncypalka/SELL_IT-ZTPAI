package com.example.sellit.model;

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
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_photo", nullable = false)
    private Long idPhoto;

    @Column(name = "photo_path", nullable = false)
    private String photoPath;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
}
