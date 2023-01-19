package ru.skypro.homework.model.user;

import lombok.Data;

@Data
public class LoginReq {
    private String password;
    private String username;

}
