package ru.skypro.homework.service.ads;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class AdsService implements AdsInterface{

    private Mapper mapper;


    /**
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из сущности в дто
     * и возвращает в api ответ в виде данных дто
     */
    @Override
    public AdList getAds() {
        Ad ad = new Ad();
        return mapper.adToAds(ad);
    }

     /**
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из сущности в дто
     * и возвращает в api ответ в виде данных дто
     */
    @Override
    public AdList getAdsMe() {
        Ad ad = new Ad();
        return mapper.adToAds(ad);
    }

    /**
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из дто в сущность
     * @param - дто (модель данных объявления)
     * @return - возвращает для api ответ в виде 
     * данных дто
     */
    @Override
    public Ads addAds(Ads ads) {
        // здесь будет операция сохранения данных в сущности
        // Ad ad = mapper.adsToAd(ads);
        // adRespoditory.save(ad);
         mapper.adsToAd(ads);
         return new Ads();
    }

    @Override
    public FullAd getFullAd(int id) {
        return mapper.adToFullAd(new Ad());
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
