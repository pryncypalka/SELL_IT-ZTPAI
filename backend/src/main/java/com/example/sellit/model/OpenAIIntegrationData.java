package com.example.sellit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "openAI_integration_data")
public class OpenAIIntegrationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prompt_id", nullable = false)
    private Long promptId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "prompt_result")
    private String promptResult;

    @Column(name = "prompt_text")
    private String promptText;

    @Column(name = "used_free_trials")
    private Integer usedFreeTrials;

    @Column(name = "token_used")
    private Integer tokenUsed;

    @Column(name = "prompt_created_at")
    private LocalDateTime promptCreatedAt;
}
