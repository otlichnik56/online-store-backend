package ru.skypro.homework.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginReq {

    private String password;
    private String username;

    public LoginReq() {}

}
