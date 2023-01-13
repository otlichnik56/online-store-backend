package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String regDate;
    private String city;
    private String image;

    public User() {

    }

    public User(String image) {
        this.image = image;
    }
}
