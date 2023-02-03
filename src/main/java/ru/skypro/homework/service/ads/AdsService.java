package ru.skypro.homework.service.ads;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.model.image.ImageDto;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class AdsService implements AdsInterface{

    private Mapper mapper;
    private final AdRepository adRepository;


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
    public Ads addAds(ru.skypro.homework.model.ad.Ad ad, MultipartFile file) {
         return mapper.addAds(ad, file);
    }


    @Override
    public FullAd getFullAd(int id) {
     
        return mapper.getFullAd(id);
    }

    @Override
    public void removeAds(int id) {
        adRepository.deleteById(id);
    }

    @Override
    public AdList updateAds(int id, ru.skypro.homework.model.ad.Ad update) {
        return mapper.updateAds(id, update);
    }

    @Override
    public ImageDto updateAdsImage(int id, MultipartFile multipartFile) throws IOException {
            return mapper.imageDtoToImage(id, multipartFile);
    }

}
