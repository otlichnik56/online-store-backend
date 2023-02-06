package ru.skypro.homework.service.user;

import ru.skypro.homework.service.ad.AdsUser;
import ru.skypro.homework.model.user.User;

public interface UserInterface {


    User getUser(String username);

    User updateUser();

    User updateUserImage();

    AdsUser getAdsMe();

}
