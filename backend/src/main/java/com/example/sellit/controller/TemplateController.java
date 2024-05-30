package com.example.sellit.controller;

import com.example.sellit.dto.TemplateDto;
import com.example.sellit.mapper.TemplateMapper;
import com.example.sellit.model.Item;
import com.example.sellit.model.Template;
import com.example.sellit.service.item.ItemService;
import com.example.sellit.service.template.TemplateService;
import com.example.sellit.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/template")
public class TemplateController {

    private final TemplateService templateService;
    private final UserService userService;
    private final ItemService itemService;

    public TemplateController(TemplateService templateService, UserService userService, ItemService itemService) {
        this.templateService = templateService;
        this.userService = userService;
        this.itemService = itemService;

    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Template>> getTemplates() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        List<Template> templates = templateService.getAllTemplates(userId);
        return new ResponseEntity<>(templates, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TemplateDto> getTemplate(@PathVariable Long id) {
        Template template = templateService.getTemplateById(id);
        return new ResponseEntity<>(TemplateMapper.toDto(template), HttpStatus.OK);
    }

    @PostMapping("/add/{itemId}")
    public ResponseEntity<TemplateDto> addTemplate(@RequestBody Template template, @PathVariable Long itemId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        Item item = itemService.getItemById(itemId);
        Template newTemplate = templateService.addTemplate(template, userId, item);
        return new ResponseEntity<>(TemplateMapper.toDto(newTemplate), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TemplateDto> updateTemplate(@PathVariable Long id, @RequestBody Template template) {
        Template updatedTemplate = templateService.updateTemplate(id, template);
        return new ResponseEntity<>(TemplateMapper.toDto(updatedTemplate), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        templateService.deleteTemplate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}