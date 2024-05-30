package com.example.sellit.mapper;

import com.example.sellit.dto.TemplateDto;
import com.example.sellit.model.Template;
import org.springframework.stereotype.Component;

@Component
public class TemplateMapper {

    public static TemplateDto toDto(Template template) {
        return TemplateDto.builder()
                .templateId(template.getTemplateId())
                .itemId(template.getItem().getItemId())
                .userId(template.getUser().getUserId())
                .title(template.getTitle())
                .description(template.getDescription())
                .createdAt(template.getCreatedAt())
                .build();
    }
}