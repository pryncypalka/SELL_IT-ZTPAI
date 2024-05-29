package com.example.sellit.dto.openAiResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usage {

    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
