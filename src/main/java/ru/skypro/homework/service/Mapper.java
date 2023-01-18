package ru.skypro.homework.service;

import org.springframework.stereotype.Service;

import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.repository.ClientRepository;

@Service
public class Mapper {

    private final ClientRepository clientRepository;

    public Mapper(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // из entity в dto  
    public User clientToUser(Client client) {
        User user = new User();
        user.setId(1);
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("email");
        user.setPhone("phone");
        user.setRegDate("reg");
        user.setCity("city");
        user.setImage("image");
        return user;
    }

    // из dto в entity
    public Client userToClient(User user) {
        Client client = new Client();
        client.setId(user.getId());
        client.setFirstName(user.getFirstName());
        client.setLastName(user.getLastName());
        client.setEmail(user.getEmail());
        client.setPhone(user.getPhone());
        client.setRegDate(user.getRegDate());
        client.setCity(user.getCity());
        client.setImage(user.getImage());
        return client;
    }

     // из entity в dto 
     public RegisterReq ClientToregisterReq(Client client) {
        RegisterReq registerReq = new RegisterReq();
        registerReq.setUsername(client.getUsername());
        registerReq.setPassword(client.getPassword());
        registerReq.setFirstName(client.getFirstName());
        registerReq.setLastName(client.getLastName());
        registerReq.setPhone(client.getPhone());
        registerReq.setRole(client.getRole());
        return registerReq;
    }

    // из dto в entity
    public Client registerReqToClient(RegisterReq registerReq) {
        Client client = new Client();
        client.setUsername(registerReq.getUsername());
        client.setPassword(registerReq.getPassword());
        client.setFirstName(registerReq.getFirstName());
        client.setLastName(registerReq.getLastName());
        client.setPhone(registerReq.getPhone());
        client.setRole(registerReq.getRole());
        return client;
    }

    // из entity в dto
    public Ads adToAds(Ad ad) {
        Ads ads = new Ads();
        ads.setAuthor(ad.getAuthor());
        ads.setImage(ad.getImage());
        ads.setPk(ad.getPk());
        ads.setPrice(ad.getPrice());
        ads.setTitle(ad.getTitle());
        return ads;
    }

    // из dto в entity
    public Ad adsToAd(Ads ads) {
        Ad ad = new Ad();
        ad.setAuthor(ads.getAuthor());
        ad.setImage(ads.getImage());
        ad.setPk(ads.getPk());
        ad.setPrice(ads.getPrice());
        ad.setTitle(ads.getTitle());
        return ad;
    }

    // из entity в dto
    public FullAd adToFullAd(Ad ad) {
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

    // из dto в entity
    public Ad FullAdToAd(FullAd fullAd) {
        Client client = clientRepository.findById(fullAd.getPk()).get();
        Ad ad = new Ad();
        ad.setAuthor(client.getId());
        ad.setImage(fullAd.getImage());
        ad.setPk(fullAd.getPk());
        ad.setPrice(fullAd.getPrice());
        ad.setTitle(fullAd.getTitle());
        return ad;
    }


}
