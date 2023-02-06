package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.service.user.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /** ПРОВЕРЕН
     *
     * @param authentication
     * @return
     */
    @GetMapping("/me")
    public User getUser(Authentication authentication) {
        return userService.getUser(authentication.getName());
    }

    /** НЕ ПРОВЕРЕН
     *
     * @param newPassword
     * @param authentication
     * @return
     */
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword,
                                        Authentication authentication) {
        System.out.println("Пароль " + newPassword);
        if (userService.setPassword(newPassword, authentication.getName())) {
            return ResponseEntity.ok().body(newPassword);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/me")
    public User updateUser(@RequestBody User user, Authentication authentication) {
        //System.out.println("Приходит JSON " + user);
        //System.out.println("Приходит authentication " + authentication);
        userService.updateUser(user, authentication);
        return user;
    }

    @PutMapping("/me/image")
    public User updateUserImage(@RequestBody User user) {
        return user;
    }

}
