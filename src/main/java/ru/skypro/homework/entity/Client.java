package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.model.user.Role;

import javax.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String regDate;
    private String city;
    private String image;

}
