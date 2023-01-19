package ru.skypro.homework.service;
import ru.skypro.homework.model.comment.CommentDto;
import ru.skypro.homework.model.comment.CommentsList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.model.Image.Image;
import ru.skypro.homework.model.ad.AdList;
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
     public RegisterReq clientToregisterReq(Client client) {
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

    /**
     * описание - метод который переводит данные из сущности в дто 
     * @param ad - сущность (таблица объявлениЯ)
     * @return - возвращает дто из двух полей:<br>
     * 1. count<br>
     * 2. List<Ads> - список объявлениЙ, 
     * который содержит объявления
     * из сущности Ad
     */

    // из entity в dto
    public AdList adToAds(Ad ad) {
        Ads ads = new Ads();
        AdList adList = new AdList();
        
        ads.setAuthor(1);
        ads.setImage(new Ads().getImage());
        ads.setPk(1);
        ads.setPrice(100);
        ads.setTitle("title");

        adList.setResults(new ArrayList<>(List.of(ads)));
        adList.setCount(1);
        return adList;
    }

    /**
     * описание - метод который переводит данные из дто в сущность 
     * @param ads - дто (модель данных)
     * @return - возвращает сущность
     */
    // из dto в entity
    public Ad adsToAd(Ads ads) {
        Ad ad = new Ad();
        Image image = new Image();
        ad.setAuthor(ads.getAuthor());
        ad.setImage(image.getImage());
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
        fullAd.setImage(new Ads().getImage());
        fullAd.setPhone(client.getPhone());
        fullAd.setPk(ad.getPk());
        fullAd.setPrice(ad.getPrice());
        fullAd.setTitle(ad.getTitle());
        return fullAd;
    }

    // из dto в entity
    public Ad fullAdToAd(FullAd fullAd) {
        Client client = clientRepository.findById(fullAd.getPk()).get();
        Ad ad = new Ad();
        ad.setAuthor(client.getId());
        ad.setImage(new Image().getImage());
        ad.setPk(fullAd.getPk());
        ad.setPrice(fullAd.getPrice());
        ad.setTitle(fullAd.getTitle());
        return ad;
    }

    // из dto в entity
    public Comment commentDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setAuthor(commentDto.getAuthor());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setPk(commentDto.getPk());
        comment.setText(commentDto.getText());
        return comment;
    }

     // из entity в dto
     public CommentsList commentToCommentDtoList(Comment comment) {
        CommentDto commentDto = new CommentDto();
        CommentsList commentsList = new CommentsList();

        commentDto.setAuthor(comment.getAuthor());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setPk(comment.getPk());
        commentDto.setText(comment.getText());

        commentsList.setResults(new ArrayList<>(Arrays.asList(commentDto)));
        commentsList.setCount(commentsList.getResults().size());
        return commentsList;
    }

      // из entity в dto
    public CommentDto commentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthor(comment.getAuthor());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setPk(comment.getPk());
        commentDto.setText(comment.getText());

        return commentDto;
    }

    

}
