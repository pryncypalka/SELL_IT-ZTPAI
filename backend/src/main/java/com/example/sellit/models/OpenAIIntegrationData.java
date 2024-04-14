package com.example.sellit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
    private Integer promptId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(name = "prompt_result")
    private String promptResult;

    @Column(name = "prompt_text")
    private String promptText;

    @Column(name = "used_free_trials")
    private Integer usedFreeTrials;

    @Column(name = "token_used")
    private Integer tokenUsed;

    @Column(name = "prompt_created_at")
    private Timestamp promptCreatedAt;
}
