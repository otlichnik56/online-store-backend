package ru.skypro.homework.service.Ads;



public interface AdsInterface {
    Ads getAds();
    Ad addAds();
    FullAd getFullAd(int id);
    void removeAds(int id);
    FullAd updateAds(int id);
    String updateAdsImage(int id);
}

