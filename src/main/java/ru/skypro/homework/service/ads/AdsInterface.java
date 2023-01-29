package ru.skypro.homework.service.ads;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.model.Image.ImageDto;
import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;

public interface AdsInterface {


    AdList getAllAds();
    Ads addAds(ru.skypro.homework.model.ad.Ad ad, MultipartFile file);
    FullAd getFullAd(int id);
    void removeAds(int id);
    FullAd updateAds(int id);
    ImageDto updateAdsImage(int id, MultipartFile multipartFile) throws IOException;
    AdList getAdsMe();

}
