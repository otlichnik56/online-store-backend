package ru.skypro.homework.service.ads;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.CreateAds;
import ru.skypro.homework.model.ad.FullAd;

public interface AdsService {


    AdList getAllAds();

    AdList getAdsMe(String username);

    FullAd getFullAd(Integer id);
    Ads addAds(Ads ads, MultipartFile file, Authentication authentication) throws IOException;
    Ads updateAds(Integer id, CreateAds update, Authentication authentication);
    boolean removeAds(Integer id, Authentication authentication);

    byte[] getImage(Integer id);

    String updateImage(Integer id, MultipartFile file);
}
