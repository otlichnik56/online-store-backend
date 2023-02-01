package ru.skypro.homework.service.user;

import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.AdsUser;
import ru.skypro.homework.model.user.User;

public interface UserInterface {

    User getUser();

    User updateUser();

    User updateUserImage();

    AdsUser getAdsMe();

}
