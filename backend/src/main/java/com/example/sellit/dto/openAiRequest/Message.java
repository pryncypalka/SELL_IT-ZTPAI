package com.example.sellit.dto.openAiRequest;

import java.util.List;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class Message {

    private String role;
    private List<Content> content;

    public Message(String role, List<Content> content) {
        this.role = role;
        this.content = content;
    }
}