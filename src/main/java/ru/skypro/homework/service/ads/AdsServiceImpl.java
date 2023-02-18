package ru.skypro.homework.service.ads;

import java.io.IOException;
import java.util.List;

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
import ru.skypro.homework.repository.CommentaryRepository;
import ru.skypro.homework.repository.PictureRepository;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class AdsServiceImpl implements AdsService {
    private UserAccessControl accessControl;
    private Mapper mapper;
    private final AdvertRepository advertRepository;
    private final CommentaryRepository commentaryRepository;
    private final ClientRepository clientRepository;
    private final PictureRepository pictureRepository;


    /** ПРОВЕРЕН
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из сущности в дто
     * и возвращает в api ответ в виде данных дто
     */
    @Override
    public AdList getAllAds() {
        List<Advert> allAds = advertRepository.getAllAds();
        return mapper.listAdvertToAdList(allAds);
    }

     /** ПРОВЕРЕН
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

    /** ПРОВЕРЕН
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

    /** ПРОВЕРЕН
     * описание - метод сервиса, который вызывает метод
     * маппера для перевода данных из дто в сущность
     * @param - дто (модель данных объявления)
     * @return - возвращает для api ответ в виде
     * данных дто
     */
    @Override
    public Ads addAds(Ads ads, MultipartFile file, Authentication authentication){
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


    /** ПРОВЕРЕН
     * Разграничивается доступ к редактированию между пользователями
     * @param id
     * @param update
     * @param authentication
     * @return
     */
    @Override
    public Ads updateAds(Integer id, CreateAds update, Authentication authentication) {
        Advert advert = advertRepository.findById(id).orElse(null);
        if (advert == null) {
            return null;
        } else{
            if (!(accessControl.accessControl(advert.getAuthor(), authentication))) {
                return null;
            } else {
                Advert result = mapper.createAdsIntoAds(advert, update);
                advertRepository.save(result);
                return mapper.advertToAds(result);
            }
        }
    }

    /** ПРОВЕРЕН
     * Разграничивается доступ к редактированию между пользователями
     * @param id
     * @param authentication
     */
    @Override
    public boolean removeAds(Integer id, Authentication authentication) {
        Advert advert = advertRepository.findById(id).orElse(null);
        if (advert == null) {
            return false;
        } else{
            if (!(accessControl.accessControl(advert.getAuthor(), authentication))) {
                return false;
            } else {
                commentaryRepository.deleteAllInBatch(commentaryRepository.findAllByAdsPk(id));
                advertRepository.deleteById(id);
                return true;
            }
        }
    }

    /** ПРОВЕРЕН
     *
     * @param id
     * @return
     */
    @Override
    public byte[] getImage(Integer id) {
        Picture picture = pictureRepository.findById(id).orElse(null);
        if (picture != null) {
            return picture.getImage();
        } else {
            return null;
        }
    }

    /** ПРОВЕРЕН
     *
     * @param id
     * @param file
     * @return
     */
    @Override
    public String updateImage(Integer id, MultipartFile file) {
        Picture picture = new Picture();
        picture.setId(id);
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
