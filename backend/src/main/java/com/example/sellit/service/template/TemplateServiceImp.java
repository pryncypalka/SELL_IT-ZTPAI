package com.example.sellit.service.template;

import com.example.sellit.model.Item;
import com.example.sellit.model.Template;
import com.example.sellit.model.User;
import com.example.sellit.repository.TemplateRepository;
import com.example.sellit.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateServiceImp implements TemplateService {

    private final TemplateRepository templateRepository;
    private final UserService userService;

    @Autowired
    public TemplateServiceImp(TemplateRepository templateRepository, UserService userService) {
        this.templateRepository = templateRepository;
        this.userService = userService;
    }

    @Override
    public List<Template> getAllTemplates(Long userId) {
        return templateRepository.findAllByUserId(userId);
    }

    @Override
    public Template getTemplateById(Long id) {
        return templateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Template with id " + id + " does not exist"));
    }

    @Override
    public Template addTemplate(Template template, Long userId, Item item) {
        User user = userService.getUser(userId);
        template.setUser(user);
        template.setItem(item);
        return templateRepository.save(template);
    }

    @Override
    public Template updateTemplate(Long id, Template template) {
        Template existingTemplate = templateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Template with id " + id + " does not exist"));

        Optional.ofNullable(template.getTitle()).ifPresent(existingTemplate::setTitle);
        Optional.ofNullable(template.getDescription()).ifPresent(existingTemplate::setDescription);

        return templateRepository.save(existingTemplate);
    }

    @Override
    public void deleteTemplate(Long id) {
        templateRepository.deleteById(id);
    }
}