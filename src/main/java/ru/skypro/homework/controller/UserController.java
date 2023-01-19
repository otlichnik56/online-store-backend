package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.service.user.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping("/me")
    public User getUser() {
        return userService.getUser();
    }

    @PostMapping("/set_password")
    public String setPassword(@RequestBody String currentPassword, @RequestBody String newPassword) {
        return "Ok";
    }

    @PutMapping("/me")
    public User updateUser(@RequestBody User user) {
        return user;
    }

    @PutMapping("/me/image")
    public User updateUserImage(@RequestBody User user) {
        return user;
    }

}
