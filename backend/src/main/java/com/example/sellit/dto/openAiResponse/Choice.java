package com.example.sellit.dto.openAiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Choice {
    private int index;
    private ResponseMessage message;
    private String finish_reason;
}
