package com.jive.controller;

import com.jive.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService service;

    public UserController(UserService service) { this.service = service; }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", service.all());
        return "index";
    }

    @PostMapping("/users")
    public String add(@RequestParam String name) {
        service.add(name);
        return "redirect:/";
    }
}