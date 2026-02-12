package com.jive.controller;

import com.jive.service.PostService;
import com.jive.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    private final UserService userService;
    private final PostService postService;

    public HomeController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("posts", postService.feed());
        return "index";
    }

    @PostMapping("/users")
    public String createUser(@RequestParam String username,
                             @RequestParam String email,
                             @RequestParam String password) {
        userService.register(username, email, password);
        return "redirect:/";
    }

    @PostMapping("/posts")
    public String createPost(@RequestParam long userId,
                             @RequestParam String content) {
        postService.createPost(userId, content);
        return "redirect:/";
    }
}