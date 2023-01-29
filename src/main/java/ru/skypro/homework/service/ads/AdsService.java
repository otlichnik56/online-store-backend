package ru.skypro.homework.service.ads;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.model.Image.ImageDto;
import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class AdsService implements AdsInterface{

    private Mapper mapper;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;


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
        return mapper.adToAds();
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
         
         return mapper.adsToAd(ads);
    }


    // дописан
    @Override
    public FullAd getFullAd(int id) {
        Ad ad = adRepository.findById(id).get();
        return mapper.adToFullAd(ad);
    }

    // дописан
    @Override
    public void removeAds(int id) {
        adRepository.deleteById(id);
    }

    @Override
    public FullAd updateAds(int id) {
        return new FullAd("descriptionupdate", 100, "titleupdate");
    }

    @Override
    public ImageDto updateAdsImage(int id, MultipartFile multipartFile) throws IOException {
            return mapper.imageDtoToImage(id, multipartFile);
    }

    public void removeComment(int id) {
        Comment comment = commentRepository.findById(id).get();
        commentRepository.delete(comment);
    }
}
