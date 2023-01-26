package ru.skypro.homework.service.ads;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.model.Image.ImageDto;
import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;

public interface AdsInterface {


    AdList getAllAds();
    Ads addAds(Ads ads);
    FullAd getFullAd(int id);
    void removeAds(int id);
    FullAd updateAds(int id);
    ImageDto updateAdsImage(int id, MultipartFile multipartFile) throws IOException;
    AdList getAdsMe();

}
