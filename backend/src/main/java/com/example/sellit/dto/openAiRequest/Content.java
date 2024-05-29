package com.example.sellit.dto.openAiRequest;



import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class Content {

    private String type;
    private String text;

    public Content(String type, String text) {
        this.type = type;
        this.text = text;
    }
}