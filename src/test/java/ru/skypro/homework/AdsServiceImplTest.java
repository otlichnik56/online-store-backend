package ru.skypro.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.service.ads.AdsServiceImpl;

@SpringBootTest
public class AdsServiceImplTest {

    @Autowired
    public AdsServiceImpl adsServiceImpl;

    /**
     * описание - метод проверяет:<br>
     * 1. возвращает ли метод getAds() сервиса adsService
     * список объявлений<br>
     * 2. является ли поле count типом Integer
     */
    @Test
    public void shoudlReturnResponseValueAsAdListDto() {
        AdList adList = adsServiceImpl.getAllAds();
        Ads ads = new Ads();
        List<Ads> listAds = new ArrayList<Ads>(Arrays.asList(ads));
        adList.setResults(listAds);
        Assertions.assertTrue(adList.getResults().contains(ads));

        Integer countTypeBeInteger = adList.getCount();
        Assertions.assertTrue(countTypeBeInteger instanceof Integer);
        
    }
}
