package com.example.sellit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "chat_data")
public class ChatData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prompt_id", nullable = false)
    private Long promptId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "used_free_trials")
    private Integer usedFreeTrials;

    @Column(name = "token_used")
    private Integer tokenUsed;

    @CreationTimestamp
    @Column(name = "prompt_created_at")
    private LocalDateTime promptCreatedAt;
}
