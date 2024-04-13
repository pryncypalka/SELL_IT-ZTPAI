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
@Table(name = "item_sub")
public class ItemSub {

    @Id
    @Column(name = "item_id", nullable = false)
    private Integer itemId;

    @Column(name = "sub_id", nullable = false)
    private Integer subId;
}
