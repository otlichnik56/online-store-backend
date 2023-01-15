package ru.skypro.homework.service.ads;

import org.springframework.stereotype.Service;
import ru.skypro.homework.model.ad.Ad;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;

@Service
public class AdsService implements AdsInterface{

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
