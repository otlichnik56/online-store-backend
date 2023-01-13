package ru.skypro.homework.service.Ads;

public class AdsService implements AdsInterface {

    @Override
    public Ads getAds() {
        return new Ads();
    }

    @Override
    public Ad addAds() {
        return new Ad();
    }

    @Override
    public FullAd getFullAd(int id) {
        return new FullAd();
    }
    @Override
    public void removeAds(int id) {

    }

    @Override
    public FullAd updateAds(int id) {
        return new FullAd("descriptionupdate", 100, "titleupdate"); 
    }

    @Override
     public String updateAdsImage(int id) {
        return "image";
    }


}

