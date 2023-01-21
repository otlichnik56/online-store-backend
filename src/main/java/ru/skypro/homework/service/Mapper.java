package ru.skypro.homework.service;
import ru.skypro.homework.model.comment.CommentDto;
import ru.skypro.homework.model.comment.CommentsList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.model.Image.Image;
import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.model.user.LoginReq;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.repository.ClientRepository;

@Service
public class Mapper {

    Logger logger = LoggerFactory.getLogger(Mapper.class);

    private final ClientRepository clientRepository;

    LoginReq loginReq = new LoginReq();

    public Mapper(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

      // из dto в entity
      public void registerReqToClient(RegisterReq registerReq) {
        Client client = new Client();
        client.setUsername(registerReq.getUsername());
        client.setPassword(registerReq.getPassword());
        client.setFirstName(registerReq.getFirstName());
        client.setLastName(registerReq.getLastName());
        client.setPhone(registerReq.getPhone());
        client.setRole(registerReq.getRole());
        clientRepository.save(client);   
    }

    public void clientToLoginReg(String userName, String password) {
        loginReq.setUsername(userName);
        loginReq.setPassword(password);

        logger.info(loginReq + " login");
        
    }
 // из entity в dto  
    public User clientToUser() {
       User user = new User();
       Client client = clientRepository.getUserName(loginReq.getUsername());
        user.setId(client.getId());
        user.setFirstName(client.getFirstName());
        user.setLastName(client.getLastName());
        user.setEmail(client.getUsername());
        user.setPhone(client.getPhone());
        user.setRegDate("");
        user.setCity("");
        user.setImage("");

        logger.info(loginReq + " loginReq");
        logger.info(client + " client");

        return user;
        
    }

  

    
    public NewPassword newPassword(String currentPass, String newPass) {
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword(currentPass);
        newPassword.setNewPassword(newPass);
        clientRepository.setNewPass(currentPass, newPass);
        return newPassword;
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
        ads.setImage(new ArrayList(Arrays.asList("1")));
        ads.setPk(1);
        ads.setPrice(100);
        ads.setTitle("ad.getTitle()");

        adList.setResults(new ArrayList<>(List.of(ads)));
        adList.setCount(adList.getResults().size());
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
