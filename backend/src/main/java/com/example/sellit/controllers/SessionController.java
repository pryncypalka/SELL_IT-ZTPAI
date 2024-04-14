package com.example.sellit.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SessionController {
    @GetMapping("/dashboard")
    public String dashboard() {
        return "Dashboard";
    }
}
