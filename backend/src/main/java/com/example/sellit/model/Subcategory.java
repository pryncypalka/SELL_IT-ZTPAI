package com.example.sellit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "subcategories")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id", nullable = false)
    private Long subcategoryId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "subcategory_name", nullable = false)
    private String subcategoryName;

    @OneToMany(mappedBy = "subcategory")
    private Set<Item> items;
}
