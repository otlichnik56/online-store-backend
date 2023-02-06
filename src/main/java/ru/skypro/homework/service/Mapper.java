package ru.skypro.homework.service;
import ru.skypro.homework.entity.Commentary;
import ru.skypro.homework.model.comment.Comment;
import ru.skypro.homework.model.comment.CommentsList;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.entity.Advert;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.entity.Picture;
import ru.skypro.homework.model.image.Image;
import ru.skypro.homework.model.user.LoginReq;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.Role;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.repository.AdvertRepository;
import ru.skypro.homework.repository.ClientRepository;
import ru.skypro.homework.repository.CommentaryRepository;
import ru.skypro.homework.repository.PictureRepository;
import ru.skypro.homework.service.ad.*;

@Service
public class Mapper {

    Logger logger = LoggerFactory.getLogger(Mapper.class);

    private final ClientRepository clientRepository;
    private final AdvertRepository advertRepository;
    private final CommentaryRepository commentaryRepository;
    private final PictureRepository pictureRepository;
    
    LoginReq loginReq = new LoginReq();
    Ads adsDto = new Ads();

    public Mapper(
        ClientRepository clientRepository, 
        AdvertRepository advertRepository,
        CommentaryRepository commentaryRepository,
        PictureRepository pictureRepository
        ) {
        this.clientRepository = clientRepository;
        this.advertRepository = advertRepository;
        this.commentaryRepository = commentaryRepository;
        this.pictureRepository = pictureRepository;
        
    }

      // из dto RegisterReq в entity Client
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

    // из entity Client в dto LoginReg
    public LoginReq clientToLoginReg(String userName, String password) {
        Client client = clientRepository.findByUsername(userName);
        loginReq.setUsername(client.getUsername());
        loginReq.setPassword(client.getPassword());
        return loginReq;
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

    // редактирование пароля, должно быть не здесь!!!!!!!!!!!!!!!!!!
    public NewPassword newPassword(String currentPass, String newPass) {
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword(currentPass);
        newPassword.setNewPassword(newPass);
        clientRepository.setNewPass(currentPass, newPass);
        return newPassword;
    }

    // из entity Client в dto AdsUser
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

    // Добавить Image. Должно быть не здесь!!!!!!!
    public Image addImage(MultipartFile imageFile) throws IOException {
        Image image = new ru.skypro.homework.model.image.Image();
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
  
    // Добавление объявлений. Должно быть не здесь!!!!!!!!!!!!!
    public Ads addAds(Ad ad, MultipartFile file) throws IOException {
        Advert adEntity = new Advert();
        Ads ads = new Ads();
        addImage(file);
        Client client = clientRepository.getUserName(loginReq.getUsername());
        adEntity.setAuthor(client.getId());
        adEntity.setImage("http://localhost:8080/image");
        adEntity.setPrice(ad.getPrice());
        adEntity.setTitle(ad.getTitle());
        adEntity.setDescription(ad.getDescription());
        advertRepository.save(adEntity);
        ads.setAuthor(client.getId());
        List<String> listImage = new ArrayList<>(Arrays.asList());
        listImage.add(adEntity.getImage());
        ads.setImage(listImage);
        ads.setPk(adEntity.getPk());
        ads.setPrice(ad.getPrice());
        ads.setTitle(ad.getTitle());

        // для отладки
        // adEntity.setAuthor(1);
        // adEntity.setImage("image");
        // adEntity.setPrice(100);
        // adEntity.setTitle("title");
        // adEntity.setDescription("desc");
        // adRepository.save(adEntity);

        // ads.setAuthor(1);
        // List<String> listImage = new ArrayList<>(Arrays.asList());
        // listImage.add(adEntity.getImage());
        // ads.setImage(listImage);
        // ads.setPk(adEntity.getPk());
        // ads.setPrice(100);
        // ads.setTitle("title");

        return ads;
    }

    // Возврат объявлений пользователя. Должно быть не здесь!!!!!!!!!!!!!
    public AdList getAdsMe() {
        AdList adList = new AdList();
        Client client = clientRepository.getUserName(loginReq.getUsername());
        List<Advert> adUser = advertRepository.getAd(1);
        if(adUser != null) {
            List<Ads> resultAds = adUser.stream()
            .map((Function<Advert, Ads>) ad -> {
                Ads ads = new Ads();
                        ads.setAuthor(ad.getAuthor());
                        ads.setImage(Arrays.asList(ad.getImage()));
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

    // Возврат всех объявлений. Должно быть не здесь!!!!!!!!!!!!!
    public AdList getAllAds() {
        AdList adList = new AdList();
        List<Advert> adUser = advertRepository.getAllAds();
        if(adUser != null) {
            List<Ads> resultAds = adUser.stream()
            .map((Function<Advert, Ads>) ad -> {
                Ads ads = new Ads();
                        ads.setAuthor(ad.getAuthor());
                        ads.setImage(Arrays.asList(ad.getImage()));
                        ads.setPk(ad.getPk());
                        ads.setPrice(ad.getPrice());
                        ads.setTitle(ad.getTitle());
                        return ads;
            }).collect(Collectors.toList());
            adList.setResults(resultAds);
            adList.setCount(resultAds.size());
            return adList;
        }

        // // для отладки
        // Ads ads = new Ads();
        // Ad ad = new Ad();

        // ad.setAuthor(1);
        // ad.setImage("http://localhost:8080/image");
        // ad.setPrice(100);
        // ad.setTitle("ad.getTitle()");
        // ad.setDescription("desc");
        // adRepository.save(ad);

        // ads.setAuthor(1);
        // ads.setImage(Arrays.asList("http://localhost:8080/image"));
        // ads.setPk(ad.getPk());
        // ads.setPrice(100);
        // ads.setTitle("ad.getTitle()");

        // List<Ads> resultAds = new ArrayList<Ads>(Arrays.asList(ads)); 
        // adList.setResults(resultAds);
        // adList.setCount(resultAds.size());
        return adList;
    }

    // Возврат полного объявления. Должно быть не здесь!!!!!!!!!!!!!
    public FullAd getFullAd(int id) {
       Advert advert = advertRepository.getFullAd(id);
       Client client = clientRepository.findById(advert.getAuthor()).orElseThrow();
       FullAd fullAd = new FullAd();
       fullAd.setAuthorFirstName(client.getFirstName());
       fullAd.setAuthorLastName(client.getLastName());
       fullAd.setDescription(advert.getDescription());
       fullAd.setEmail(client.getEmail());
       fullAd.setImage(advertRepository.getImages(client.getId()));
       fullAd.setPhone(client.getPhone());
       fullAd.setPk(advert.getPk());
       fullAd.setPrice(advert.getPrice());
       fullAd.setTitle(advert.getTitle());
       return fullAd; 
    }

    // Редактирование объявления. Должно быть не здесь!!!!!!!!!!!!!
     public AdList updateAds(int id, Ad update) {
       Ads ads = new Ads();
       AdList adList = new AdList();
       Client client = clientRepository.getUserName(loginReq.getUsername());
       ads.setPrice(update.getPrice());
       ads.setTitle(update.getTitle());
       ads.setImage(advertRepository.getImage(id));
       ads.setAuthor(client.getId());
       ads.setPk(id);
       advertRepository.updateAd(id, update.getPrice(), update.getTitle(), update.getDescription());
       List<Ads> resultAds = new ArrayList<Ads>(Arrays.asList(ads)); 
       adList.setResults(resultAds);
       adList.setCount(resultAds.size());
       return adList; 
    }

    // из dto Comment в entity Commentary
    public Commentary commentToCommentary(int adPk, Comment comment) {
        Commentary commentary = new Commentary();
        commentary.setAuthor(comment.getAuthor());
        commentary.setCreatedAt(comment.getCreatedAt());
        commentary.setText(comment.getText());
        commentary.setAdsPk(adPk);
        return commentary;
    }

     // из entity Commentary в dto CommentsList
     public CommentsList commentaryToCommentsList(int adPk) {
        CommentsList commentsList = new CommentsList();
        Client client = clientRepository.getUserName(loginReq.getUsername());
        List<Commentary> resultCommentaries = commentaryRepository.getComments(adPk);
        if(resultCommentaries != null) {
            List<Comment> commentList = resultCommentaries.stream()
             .map((Function<Commentary, Comment>) commentary -> {
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

      // из entity Commentary в dto Comment
      public Comment commentaryToComment(int id) {
          Commentary commentary = commentaryRepository.findById(id).get();
          Comment comment = new Comment();
          comment.setAuthor(commentary.getAuthor());
          comment.setCreatedAt(commentary.getCreatedAt());
          comment.setPk(commentary.getPk());
          comment.setText(commentary.getText());
          return comment;
      }   

}
