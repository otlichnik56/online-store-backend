package ru.skypro.homework.service;

import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.repository.ClientRepository;

public class Mapper {

    private final ClientRepository clientRepository;

    public Mapper(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public User clientInUser(Client client) {
        User user = new User();
        user.setId(client.getId());
        user.setFirstName(client.getFirstName());
        user.setLastName(client.getLastName());
        user.setEmail(client.getEmail());
        user.setPhone(client.getPhone());
        user.setRegDate(client.getRegDate());
        user.setCity(client.getCity());
        user.setImage(client.getImage());
        return user;
    }

    public Client registerReqInClient(RegisterReq registerReq) {
        Client client = new Client();
        client.setUsername(registerReq.getUsername());
        client.setPassword(registerReq.getPassword());
        client.setFirstName(registerReq.getFirstName());
        client.setLastName(registerReq.getLastName());
        client.setPhone(registerReq.getPhone());
        client.setRole(registerReq.getRole());
        return client;
    }

    public Ads adInAds(Ad ad) {
        Ads ads = new Ads();
        ads.setAuthor(ad.getAuthor());
        ads.setImage(ad.getImage());
        ads.setPk(ad.getPk());
        ads.setPrice(ad.getPrice());
        ads.setTitle(ad.getTitle());
        return ads;
    }

    public FullAd adInFullAd(Ad ad) {
        Client client = clientRepository.findById(ad.getAuthor()).get();
        FullAd fullAd = new FullAd();
        fullAd.setAuthorFirstName(client.getFirstName());
        fullAd.setAuthorLastName(client.getLastName());
        fullAd.setDescription(ad.getDescription());
        fullAd.setEmail(client.getEmail());
        fullAd.setImage(client.getImage());
        fullAd.setPhone(client.getPhone());
        fullAd.setPk(ad.getPk());
        fullAd.setPrice(ad.getPrice());
        fullAd.setTitle(ad.getTitle());
        return fullAd;
    }


}
