package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.user.User;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public User getUser() {
        return new User();
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
