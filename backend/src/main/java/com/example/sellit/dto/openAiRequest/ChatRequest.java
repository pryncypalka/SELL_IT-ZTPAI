package com.example.sellit.dto.openAiRequest;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRequest {

    private String model;
    private List<Message> messages;
    private double temperature = 1;
    private int max_tokens = 500;
    private double top_p = 1;
    private double frequency_penalty = 0;
    private double presence_penalty = 0;

    public ChatRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

}