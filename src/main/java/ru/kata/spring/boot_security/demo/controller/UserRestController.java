package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
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
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = userService.getAllUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/{id}")
//    public User getUser(@PathVariable long id, @ModelAttribute("user") User user) {
//        return userService.getUserById(id);
//    }
//
//
//    @PostMapping("/new-user")
//    public User createNewUser(@ModelAttribute("newUser") User newUser, @ModelAttribute("role") String role) {
//        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
//        userService.saveUser(newUser, role);
//        return newUser;
//    }
//
//
//    @PutMapping("/{id}")
//    public User update(@ModelAttribute("user") User user, @ModelAttribute("role") String role) {
//        userService.updateUser(user, role);
//        return user;
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable long id) {
//        userService.removeUser(id);
//        return "Ok";
//    }
}
