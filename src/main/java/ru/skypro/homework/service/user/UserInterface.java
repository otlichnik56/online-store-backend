package ru.skypro.homework.service.user;

import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.entity.User;

public interface UserInterface {

    User getUser();

    User updateUser();

    User updateUserImage();

    Ads getAdsMe();

}
