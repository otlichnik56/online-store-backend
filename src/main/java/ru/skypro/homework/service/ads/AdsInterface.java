package ru.skypro.homework.service.ads;

import java.util.List;

import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;

public interface AdsInterface {


    AdList getAds();
    Ads addAds(Ads ads);
    FullAd getFullAd(int id);
    void removeAds(int id);
    FullAd updateAds(int id);
    String updateAdsImage(int id);
    AdList getAdsMe();

}
