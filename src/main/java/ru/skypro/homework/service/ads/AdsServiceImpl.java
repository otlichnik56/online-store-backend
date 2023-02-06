package ru.skypro.homework.service.ads;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Advert;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.entity.Picture;
import ru.skypro.homework.model.ad.CreateAds;
import ru.skypro.homework.model.image.Image;
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

    Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);
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
        Client client = clientRepository.getUserName(authentication.getName());
        Advert advert = mapper.adsToAdvert(ads, client);
        advertRepository.save(advert);
        addImage(file);
        return ads;
    }


    @Override
    public Ads updateAds(Integer id, CreateAds update) {
        Advert advert = advertRepository.findById(id).orElse(null);
        if (advert == null) {
            return null;
        } else {
            Advert result = mapper.createAdsIntoAds(advert, update);
            advertRepository.save(result);
            return mapper.advertToAds(result);
        }
    }

    @Override
    public void removeAds(Integer id) {
        advertRepository.deleteById(id);
    }



    public Image addImage(MultipartFile imageFile) {
        Image image = new Image();
        Picture picture = new Picture();
        try (
                InputStream inputStream = imageFile.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
        ) {
            byte[] imageByteas = bufferedInputStream.readAllBytes();
            picture.setFileName(imageFile.getOriginalFilename());
            picture.setFileSize(imageFile.getSize());
            picture.setMediaType(imageFile.getContentType());
            picture.setFileName(imageFile.getOriginalFilename());
            picture.setData(imageByteas);
        } catch(IOException exception) {
            logger.info(exception.getMessage());
        }
        pictureRepository.save(picture);
        List<String> getAddedImages = pictureRepository.getAddedImages();
        image.setImage(getAddedImages);
        return image;
    }

}
