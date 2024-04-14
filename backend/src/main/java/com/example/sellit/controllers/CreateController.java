package com.example.sellit.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CreateController {
    @GetMapping("/create")
    public String create() {
        return "Create";
    }
}
