package com.example.sellit.service.template;

import com.example.sellit.model.Item;
import com.example.sellit.model.Template;

import java.util.List;

public interface TemplateService {
    List<Template> getAllTemplates(Long userId);

    Template getTemplateById(Long id);

    Template addTemplate(Template template, Long userId, Item item);

    Template updateTemplate(Long id, Template template);

    void deleteTemplate(Long id);
}
