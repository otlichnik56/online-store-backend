package ru.skypro.homework.service.user;

import org.springframework.stereotype.Service;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.user.User;

@Service
public class UserService implements UserInterface {

    @Override
    public User getUser() {
        return new User();
    }

    @Override
    public User updateUser() {
        return new User(1, "jon", "slith", "test@mail.ru", "89991234545",
                "123", "Moscow", "image" );
    }

    @Override
    public User updateUserImage() {
        return new User("imageUpdate");
    }

    @Override
    public Ads getAdsMe() {
        return new Ads();
    }
}
