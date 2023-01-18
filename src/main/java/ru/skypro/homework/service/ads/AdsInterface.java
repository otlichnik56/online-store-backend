package ru.skypro.homework.service.ads;

import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;

public interface AdsInterface {

    Ads getAds();
    Ad addAds();
    FullAd getFullAd(int id);
    void removeAds(int id);
    FullAd updateAds(int id);
    String updateAdsImage(int id);

}
