package ru.skypro.homework.service.user;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.model.user.User;

public interface UserService {

    User getUser(String username);

    boolean setPassword(NewPassword newPassword, String username);

    User updateUser(User user, Authentication authentication);

    boolean updateUserImage(MultipartFile file, Authentication authentication);

}
