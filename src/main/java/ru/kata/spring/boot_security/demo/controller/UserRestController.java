package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception.IdNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id, @ModelAttribute("user") User user) {
        return userService.getUserById(id);
    }


    @PostMapping("/new-user")
    public User createNewUser(@ModelAttribute("newUser") User newUser, @ModelAttribute("role") String role) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userService.saveUser(newUser, role);
        return newUser;
    }


    @PutMapping("/{id}")
    public User update(@ModelAttribute("user") User user, @ModelAttribute("role") String role)
            throws IdNotFoundException{
        userService.updateUser(user, role);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) throws IdNotFoundException {
        userService.removeUser(id);
        return "Ok";
    }
}
