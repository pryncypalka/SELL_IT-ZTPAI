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
    @Column(name = "prompt_id", nullable = false)
    private String promptId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "token_used")
    private Integer tokenUsed;

    @CreationTimestamp
    @Column(name = "prompt_created_at")
    private LocalDateTime promptCreatedAt;
}
