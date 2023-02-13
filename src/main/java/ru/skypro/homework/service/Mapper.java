package ru.skypro.homework.service;
import ru.skypro.homework.entity.Commentary;

import ru.skypro.homework.model.comment.Comment;
import ru.skypro.homework.model.comment.CommentsList;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.ad.*;
import ru.skypro.homework.entity.Advert;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.Role;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.repository.AdvertRepository;
import ru.skypro.homework.repository.ClientRepository;
import ru.skypro.homework.repository.CommentaryRepository;

@Service
public class Mapper {

    private final Logger logger = LoggerFactory.getLogger(Mapper.class);

    private final ClientRepository clientRepository;
    private final AdvertRepository advertRepository;
    private final CommentaryRepository commentaryRepository;
    

    public Mapper(
        ClientRepository clientRepository, 
        AdvertRepository advertRepository,
        CommentaryRepository commentaryRepository
        ) {
        this.clientRepository = clientRepository;
        this.advertRepository = advertRepository;
        this.commentaryRepository = commentaryRepository;
        
    }

    // из entity Client в dto User
    public User clientToUser(Client client) {
       User user = new User();
       user.setId(client.getId());
       user.setFirstName(client.getFirstName());
       user.setLastName(client.getLastName());
       user.setEmail(client.getUsername());
       user.setPhone(client.getPhone());
       user.setRegDate("");
       user.setCity("");
       user.setImage("");
       return user;
    }


    //
    public Advert adsToAdvert(Ads ads, Client client, Integer pictureId) {
        Advert advert = new Advert();
        advert.setImage("/image/" + pictureId);
        advert.setPrice(ads.getPrice());
        advert.setTitle(ads.getTitle());
        advert.setAuthor(client.getId());
        return advert;
    }

    //
    public Ads advertToAds(Advert advert) {
        Ads ads = new Ads();
        ads.setAuthor(advert.getAuthor());
        ads.setImage(ads.getImage());
        ads.setPk(advert.getPk());
        ads.setPrice(advert.getPrice());
        ads.setTitle(advert.getTitle());
        return ads;
    }

    //
    public AdList listAdvertToAdList(List<Advert> adsMe) {
        AdList adsList = new AdList();
        if(adsMe != null) {
            List<Ads> resultAds = adsMe.stream()
            .map(this::advertToAds).collect(Collectors.toList());
            adsList.setResults(resultAds);
            adsList.setCount(resultAds.size());
            return adsList;
        }
        return adsList;
    }

    // Создание FullAd из Advert и Client
    public FullAd linkageFullAd(Advert advert, Client client) {
       FullAd fullAd = new FullAd();
       fullAd.setAuthorFirstName(client.getFirstName());
       fullAd.setAuthorLastName(client.getLastName());
       fullAd.setDescription(advert.getDescription());
       fullAd.setEmail(client.getEmail());
       fullAd.setImage(advert.getImage());
       fullAd.setPhone(client.getPhone());
       fullAd.setPk(advert.getPk());
       fullAd.setPrice(advert.getPrice());
       fullAd.setTitle(advert.getTitle());
       return fullAd; 
    }

    //
     public Advert createAdsIntoAds(Advert advert, CreateAds update) {
       advert.setPrice(update.getPrice());
       advert.setTitle(update.getTitle());
       advert.setDescription(update.getDescription());
       return advert;
    }

    // из dto Comment в entity Commentary
    public Commentary commentToCommentary(Integer adPk, Comment comment) {
        Commentary commentary = new Commentary();
        commentary.setAuthor(comment.getAuthor());
        commentary.setCreatedAt(comment.getCreatedAt());
        commentary.setText(comment.getText());
        commentary.setAdsPk(adPk);
        return commentary;
    }

    public Commentary commentToCommentaryEdit(Commentary commentary, Comment comment) {
        commentary.setAuthor(comment.getAuthor());
        commentary.setCreatedAt(comment.getCreatedAt());
        commentary.setText(comment.getText());
        return commentary;
    }

     // из entity Commentary в dto Comment
     public Comment commentaryToComment(Commentary commentary) {
         Comment comment = new Comment();
         comment.setAuthor(commentary.getAuthor());
         comment.setCreatedAt(commentary.getCreatedAt());
         comment.setPk(commentary.getPk());
         comment.setText(commentary.getText());
         return comment;
     }

    // из entity Commentary в dto CommentsList
     public CommentsList commentaryToCommentsList(List<Commentary> comments) {
        CommentsList commentsList = new CommentsList();
        if(comments != null) {
            List<Comment> commentList = comments.stream()
             .map(commentary -> {
                Comment comment = new Comment();
                comment.setAuthor(commentary.getAuthor());
                comment.setCreatedAt(commentary.getCreatedAt());
                comment.setPk(commentary.getPk());
                comment.setText(commentary.getText());
                return comment;
            }).collect(Collectors.toList());
            commentsList.setResults(commentList);
            commentsList.setCount(commentList.size());
            return commentsList;
        }
        return commentsList;
    }


    // из dto User в entity Client
    public Client userToClient(User user, Client client) {
        client.setFirstName(user.getFirstName());
        client.setLastName(user.getLastName());
        client.setEmail(user.getEmail());
        client.setPhone(user.getPhone());
        client.setRegDate(user.getRegDate());
        client.setCity(user.getCity());
        client.setImage(user.getImage());
        return client;
    }

    // из dto в entity
    public Client registerReqToClient(RegisterReq registerReq) {
        Client client = new Client();
        client.setUsername(registerReq.getUsername());
        client.setPassword(registerReq.getPassword());
        client.setFirstName(registerReq.getFirstName());
        client.setLastName(registerReq.getLastName());
        client.setPhone(registerReq.getPhone());
        client.setRole(Role.USER);
        return client;
    }

}
