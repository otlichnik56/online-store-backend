package ru.skypro.homework.service.ads;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.UserAccessControl;
import ru.skypro.homework.entity.Advert;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.entity.Picture;
import ru.skypro.homework.model.ad.CreateAds;
import ru.skypro.homework.repository.ClientRepository;
import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.repository.AdvertRepository;
import ru.skypro.homework.repository.PictureRepository;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);
    private UserAccessControl accessControl;
    private Mapper mapper;
    private final AdvertRepository advertRepository;
    private final ClientRepository clientRepository;
    private final PictureRepository pictureRepository;


    /** НЕ ПРОВЕРЕН
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из сущности в дто
     * и возвращает в api ответ в виде данных дто
     */
    @Override
    public AdList getAllAds() {
        List<Advert> allAds = advertRepository.getAllAds();
        return mapper.listAdvertToAdList(allAds);
    }

     /** НЕ ПРОВЕРЕН
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из сущности в дто
     * и возвращает в api ответ в виде данных дто
     */
    @Override
    public AdList getAdsMe(String username) {
        Client user = clientRepository.findByUsername(username);
        List<Advert> adsUser = advertRepository.getAd(user.getId());
        return mapper.listAdvertToAdList(adsUser);
    }

    /** НЕ ПРОВЕРЕН
     *
     * @param id
     * @return
     */
    @Override
    public FullAd getFullAd(Integer id) {
        Advert advert = advertRepository.findById(id).orElse(null);
        if (advert == null) {
            return null;
        } else {
            Client client = clientRepository.findById(advert.getAuthor()).orElseThrow();
            return mapper.linkageFullAd(advert, client);
        }
    }

    /** НЕ ПРОВЕРЕН
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из дто в сущность
     * @param - дто (модель данных объявления)
     * @return - возвращает для api ответ в виде
     * данных дто
     */
    @Override
    public Ads addAds(Ads ads, MultipartFile file, Authentication authentication) throws IOException {
        Picture picture = new Picture();
        try {
            byte[] bytes = file.getBytes();
            picture.setImage(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Picture pictureSave = pictureRepository.save(picture);
        Client client = clientRepository.getUserName(authentication.getName());
        Advert advert = mapper.adsToAdvert(ads, client, pictureSave.getId());
        advertRepository.save(advert);
        return ads;
    }


    /** НЕ ПРОВЕРЕН
     * Разграничивается доступ к редактированию между пользователями
     * @param id
     * @param update
     * @param authentication
     * @return
     */
    @Override
    public Ads updateAds(Integer id, CreateAds update, Authentication authentication) {
        Advert advert = advertRepository.findById(id).orElse(null);
        assert advert != null;
        if (accessControl.accessControl(advert.getAuthor(), authentication)) {
            Advert result = mapper.createAdsIntoAds(advert, update);
            advertRepository.save(result);
            return mapper.advertToAds(result);
        } else {
            return null;
        }
    }

    /** НЕ ПРОВЕРЕН
     * Разграничивается доступ к редактированию между пользователями
     * @param id
     * @param authentication
     */
    @Override
    public void removeAds(Integer id, Authentication authentication) {
        Advert advert = advertRepository.findById(id).orElse(null);
        assert advert != null;
        if (accessControl.accessControl(advert.getAuthor(), authentication)) {
            advertRepository.deleteById(id);
        }
    }

    @Override
    public byte[] getImage(Integer id) {
        Picture picture = pictureRepository.findById(id).orElse(null);
        if (picture != null) {
            return picture.getImage();
        } else {
            return null;
        }
    }

    @Override
    public String setImage(Integer id, MultipartFile file) {
        Picture picture = pictureRepository.findById(id).orElseThrow();
        try {
            byte[] bytes = file.getBytes();
            picture.setImage(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pictureRepository.save(picture);
        return "/image/" + picture.getId();
    }


}
