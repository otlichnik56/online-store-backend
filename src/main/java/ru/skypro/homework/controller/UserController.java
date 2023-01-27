package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.service.user.PasswordService;
import ru.skypro.homework.service.user.UserService;


@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordService passwordService;

    @GetMapping("/me")
    public User getUser() {
         return userService.getUser();
    }

    @PostMapping("/set_password")
    public NewPassword setPassword(@RequestBody(required = true) NewPassword newPassword) {
        return passwordService.setPassword(newPassword.getCurrentPassword(), newPassword.getNewPassword());
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
