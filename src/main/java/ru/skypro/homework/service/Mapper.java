package ru.skypro.homework.service;
import ru.skypro.homework.model.comment.CommentDto;
import ru.skypro.homework.model.comment.CommentsList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.model.Image.Image;
import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.AdsUser;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.model.user.LoginReq;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ClientRepository;
import ru.skypro.homework.repository.CommentRepository;

@Service
public class Mapper {

    Logger logger = LoggerFactory.getLogger(Mapper.class);

    private final ClientRepository clientRepository;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;

    LoginReq loginReq = new LoginReq();
    Ads adsDto = new Ads();
    Image image = new Image();

    public Mapper(
        ClientRepository clientRepository, 
        AdRepository adRepository,
        CommentRepository commentRepository
        ) {
        this.clientRepository = clientRepository;
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
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
        logger.info(user + " user");

        return user;
        
    }

  

    
    public NewPassword newPassword(String currentPass, String newPass) {
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword(currentPass);
        newPassword.setNewPassword(newPass);
        clientRepository.setNewPass(currentPass, newPass);
        return newPassword;
    }

    // from entity to dto 
    public AdsUser clientToAdsUser() {
        Client client = clientRepository.getUserName(loginReq.getUsername());
        AdsUser adsUser = new AdsUser();
        adsUser.setFirstName(client.getFirstName());
        adsUser.setLastName(client.getLastName());
        adsUser.setPassword(client.getPassword());
        adsUser.setPhone(client.getPhone());
        adsUser.setRole(client.getRole());
        adsUser.setUsername(client.getUsername());
        return adsUser;
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

    /**
     * описание - метод который переводит данные из дто в сущность 
     * @param ads - дто (модель данных)
     * @return - возвращает сущность
     */

    // из dto в entity
    public Ads adsToAd(Ads ads) {
       
        Ad ad = new Ad();
        Client client = clientRepository.getUserName(loginReq.getUsername());
        ad.setAuthor(client.getId());
        ad.setImage(image.getImage());
        ad.setPk(ads.getPk());
        
        ad.setPrice(ads.getPrice());
        ad.setTitle(ads.getTitle());

        adRepository.save(ad);
        return ads;
    }

    // из entity в dto
    public AdList adToAds() {
       
        AdList adList = new AdList();
        Client client = clientRepository.getUserName(loginReq.getUsername());
        List<Ad> adUser = adRepository.getAd(client.getId());
        logger.info(adUser + " aduser");
        if(adUser != null) {
            List<Ads> resultAds = adUser.stream()
            .map((Function<Ad, Ads>) ad -> {
                Ads ads = new Ads();
                        ads.setAuthor(ad.getAuthor());
                        ads.setImage(new ArrayList(Arrays.asList("1")));
                        ads.setPk(ad.getPk());
                        ads.setPrice(ad.getPrice());
                        ads.setTitle(ad.getTitle());
                        return ads;
            }).collect(Collectors.toList());
           
            adList.setResults(resultAds);
            adList.setCount(resultAds.size());
            return adList;
        }

        return adList;
       
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
    public CommentDto commentDtoToComment(int adPk) {
        Comment comment = new Comment();
        Client client = clientRepository.getUserName(loginReq.getUsername());
        comment.setPk(adPk);
        comment.setAuthor(client.getId());
        comment.setCreatedAt(LocalDate.now().toString());
        comment.setText("text");
        commentRepository.save(comment);
        return new CommentDto(comment.getPk(),comment.getCreatedAt(), comment.getText(), comment.getAuthor());
        
    }

     // из entity в dto
     public CommentsList commentToCommentDtoList(int adPk) {
        CommentsList commentsList = new CommentsList();

        Client client = clientRepository.getUserName(loginReq.getUsername());
        List<Comment> resultComments = commentRepository.getComments(adPk);

     
        if(resultComments != null) {
            List<CommentDto> commentDtoList = resultComments.stream()
             .map((Function<Comment, CommentDto>) comment -> {
                CommentDto commentDto = new CommentDto();
                commentDto.setAuthor(comment.getAuthor());
                commentDto.setCreatedAt(comment.getCreatedAt());
                commentDto.setPk(comment.getPk());
                commentDto.setText(comment.getText());
                return commentDto;

            }).collect(Collectors.toList());
        
            commentsList.setResults(commentDtoList);
            commentsList.setCount(commentDtoList.size());
            return commentsList;
        }
        
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
