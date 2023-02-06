package ru.skypro.homework.service.user;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.model.user.User;

public interface UserService {

    User getUser(String username);

    boolean setPassword(NewPassword newPassword, String username);

    User updateUser(User user, Authentication authentication);

    User updateUserImage();

}
