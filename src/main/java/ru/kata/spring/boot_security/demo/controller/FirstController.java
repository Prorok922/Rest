package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class FirstController {
    private final UserService userService;

    public FirstController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String homePage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

//    @GetMapping("/user")
//    public String showUser(Principal principal, Model model) {
//        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
//        return "userpage1";
//    }

    @GetMapping("/user")
    public String showUserByEmail(Model model) {
        model.addAttribute("user", userService.loadUserByUsername(SecurityContextHolder
                .getContext().getAuthentication().getName()));
        return "userpage1";
    }
}
