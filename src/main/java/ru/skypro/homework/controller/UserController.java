package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.service.user.PasswordService;
import ru.skypro.homework.service.user.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordService passwordService;

    /** ПРОВЕРЕН
     *
     * @param authentication
     * @return
     */
    @GetMapping("/me")
    public User getUser(Authentication authentication) {
        return userService.getUser(authentication.getName());
    }

    @PostMapping("/set_password")
    public NewPassword setPassword(@RequestBody NewPassword newPassword,
                                   Authentication authentication) {
        authentication.getName();
        return passwordService.setPassword(newPassword.getCurrentPassword(), newPassword.getNewPassword());
    }

    @PutMapping("/me")
    public User updateUser(@RequestBody User user, Authentication authentication) {

        return user;
    }

    @PutMapping("/me/image")
    public User updateUserImage(@RequestBody User user) {
        return user;
    }

}
