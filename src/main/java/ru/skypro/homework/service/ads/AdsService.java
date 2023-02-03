package ru.skypro.homework.service.ads;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import ru.skypro.homework.service.ad.Ad;
import ru.skypro.homework.service.ad.AdList;
import ru.skypro.homework.service.ad.Ads;
import ru.skypro.homework.service.ad.FullAd;
import ru.skypro.homework.repository.AdvertRepository;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class AdsService implements AdsInterface{

    private Mapper mapper;
    private final AdvertRepository advertRepository;


    /**
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из сущности в дто
     * и возвращает в api ответ в виде данных дто
     */
    @Override
    public AdList getAllAds() {
        return mapper.getAllAds();
    }

     /**
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из сущности в дто
     * и возвращает в api ответ в виде данных дто
     */
    @Override
    public AdList getAdsMe() {
        return mapper.getAdsMe();
    }

    /**
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из дто в сущность
     * @param - дто (модель данных объявления)
     * @return - возвращает для api ответ в виде 
     * данных дто
     */
    @Override
    public Ads addAds(Ad ad, MultipartFile file) throws IOException {
         return mapper.addAds(ad, file);
    }


    @Override
    public FullAd getFullAd(int id) {
     
        return mapper.getFullAd(id);
    }

    @Override
    public void removeAds(int id) {
        advertRepository.deleteById(id);
    }

    @Override
    public AdList updateAds(int id, Ad update) {
        return mapper.updateAds(id, update);
    }

    // @Override
    // public ImageDto updateAdsImage(int id, MultipartFile multipartFile) throws IOException {
    //         return mapper.addImage(id, multipartFile);
    // }

}
