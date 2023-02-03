package ru.skypro.homework.service.ads;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.service.ad.Ad;
import ru.skypro.homework.service.ad.AdList;
import ru.skypro.homework.service.ad.Ads;
import ru.skypro.homework.service.ad.FullAd;

public interface AdsInterface {


    AdList getAllAds();
    Ads addAds(Ad ad, MultipartFile file) throws IOException;
    FullAd getFullAd(int id);
    void removeAds(int id);
    AdList updateAds(int id, Ad update);
    // ImageDto updateAdsImage(int id, MultipartFile multipartFile) throws IOException;
    AdList getAdsMe();

}
