package ru.skypro.homework.model.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.skypro.homework.model.user.Role;

@Data
@AllArgsConstructor
public class AdsUser {
    
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    public AdsUser() {
        
    }
}
