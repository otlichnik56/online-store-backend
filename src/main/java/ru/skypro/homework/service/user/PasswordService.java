package ru.skypro.homework.service.user;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class PasswordService {

    private Mapper mapper;

    public NewPassword setPassword(String currentPassword, String newPassword) {
        return mapper.newPassword(currentPassword, newPassword);
       
    }
}
