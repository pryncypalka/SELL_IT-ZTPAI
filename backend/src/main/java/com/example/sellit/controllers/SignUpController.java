package com.example.sellit.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SignUpController {
    @GetMapping("/signup")
    public String signup() {
        return "Signup";
    }
}
