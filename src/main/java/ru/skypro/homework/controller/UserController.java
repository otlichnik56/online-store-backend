package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.service.user.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    /** ПРОВЕРЕН
     *
     * @param authentication
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/me")

    public User getUser(Authentication authentication) {
        logger.info("UserController. method getUser. Username = " + authentication.getName());
        return userService.getUser(authentication.getName());
    }

    /** Надо поправить
     *
     * @param newPassword
     * @param authentication
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword,
                                        Authentication authentication) {
        if (userService.setPassword(newPassword, authentication.getName())) {
            return ResponseEntity.ok().body(newPassword);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /** Работает
     *
     * @param user
     * @param authentication
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/me")
    public User updateUser(@RequestBody User user, Authentication authentication) {
        logger.info("UserController. method updateUser, user = " + user + ". authentication = " + authentication.getName());
        userService.updateUser(user, authentication);
        return user;
    }

    /** НЕ ПРОВЕРЕН до конца
     *
     * @param file
     * @param authentication
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PatchMapping(value = "/me/image", consumes = "multipart/form-data")
    public ResponseEntity<?> updateUserImage(@RequestPart(value = "image") MultipartFile file,
                                             Authentication authentication) {
        if (userService.updateUserImage(file, authentication)) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

}
