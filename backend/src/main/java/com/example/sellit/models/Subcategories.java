package com.example.sellit.models;

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
public class Subcategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id", nullable = false)
    private Long subcategoryId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private  Categories categories;

    @Column(name = "subcategory_name", nullable = false)
    private String subcategoryName;

    @ManyToMany(mappedBy = "subcategories")
    private Set<Item> items;
}
