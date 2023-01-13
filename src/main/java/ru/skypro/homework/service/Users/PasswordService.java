package ru.skypro.homework.service.Users;

import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public Password setPassword() {
        return new Password();
    }
}