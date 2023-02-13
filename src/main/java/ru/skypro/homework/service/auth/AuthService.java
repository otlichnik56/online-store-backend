package ru.skypro.homework.service.auth;

import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq);
}
