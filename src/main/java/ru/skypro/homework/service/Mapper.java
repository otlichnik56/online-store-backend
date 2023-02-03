package ru.skypro.homework.service;
import ru.skypro.homework.model.comment.CommentDto;
import ru.skypro.homework.model.comment.CommentsList;
import ru.skypro.homework.model.image.ImageDto;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.AdsUser;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.model.user.LoginReq;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.Role;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ClientRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageRepository;

@Service
public class Mapper {

    Logger logger = LoggerFactory.getLogger(Mapper.class);

    private final ClientRepository clientRepository;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    
    LoginReq loginReq = new LoginReq();
    Ads adsDto = new Ads();

    public Mapper(
        ClientRepository clientRepository, 
        AdRepository adRepository,
        CommentRepository commentRepository,
        ImageRepository imageRepository
        ) {
        this.clientRepository = clientRepository;
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
        this.imageRepository = imageRepository;
        
    }

      // из dto в entity
      public void registerReqToClient(RegisterReq registerReq) {
        Client client = new Client();
        client.setUsername(registerReq.getUsername());
        client.setPassword(registerReq.getPassword());
        client.setFirstName(registerReq.getFirstName());
        client.setLastName(registerReq.getLastName());
        client.setPhone(registerReq.getPhone());
        client.setRole(Role.USER);
        clientRepository.save(client);   
    }

    public void clientToLoginReg(String userName, String password) {
        Client client = clientRepository.findByUsername(userName);
        loginReq.setUsername(client.getUsername());
        loginReq.setPassword(client.getPassword());
        
        logger.info(loginReq + " login");
        
    }

    // из entity в dto  
    public User clientToUser() {
       User user = new User();
       Client client = clientRepository.getUserName(loginReq.getUsername());
       logger.info(client + " client");
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
     * @param  - дто (модель данных)
     * @return - возвращает сущность
     */

    public Ads addAds(ru.skypro.homework.model.ad.Ad ad, MultipartFile file) {
       
        Ad adEntity = new Ad();
        Ads ads = new Ads();
       
        Client client = clientRepository.getUserName(loginReq.getUsername());
      
        // adEntity.setAuthor(client.getId());
        // adEntity.setImage(file.getOriginalFilename());
        // adEntity.setPrice(ad.getPrice());
        // adEntity.setTitle(ad.getTitle());
        // adEntity.setDescription(ad.getDescription());
        // adRepository.save(adEntity);

        // ads.setAuthor(client.getId());
        // List<String> listImage = new ArrayList<>(Arrays.asList());
        // listImage.add(adEntity.getImage());
        // ads.setImage(listImage);
        // ads.setPk(adEntity.getPk());
        // ads.setPrice(ad.getPrice());
        // ads.setTitle(ad.getTitle());

        // для отладки
        adEntity.setAuthor(1);
        adEntity.setImage("image");
        adEntity.setPrice(100);
        adEntity.setTitle("title");
        adEntity.setDescription("desc");
        adRepository.save(adEntity);

        ads.setAuthor(1);
        List<String> listImage = new ArrayList<>(Arrays.asList());
        listImage.add(adEntity.getImage());
        ads.setImage(listImage);
        ads.setPk(adEntity.getPk());
        ads.setPrice(100);
        ads.setTitle("title");

        logger.info(ads + " ads");
      
        return ads;
    }

    public AdList getAdsMe() {
       
        AdList adList = new AdList();
     
        Client client = clientRepository.getUserName(loginReq.getUsername());
        
        List<Ad> adUser = adRepository.getAd(1);
        logger.info(adUser + " aduser");
        
        if(adUser != null) {
            List<Ads> resultAds = adUser.stream()
            .map((Function<Ad, Ads>) ad -> {
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

       public AdList getAllAds() {
       
        AdList adList = new AdList();
        
        // List<Ad> adUser = adRepository.getAllAds();
        // logger.info(adUser + " aduser");
        // if(adUser != null) {
        //     List<Ads> resultAds = adUser.stream()
        //     .map((Function<Ad, Ads>) ad -> {
        //         Ads ads = new Ads();
        //                 ads.setAuthor(ad.getAuthor());
        //                 ads.setImage(Arrays.asList(ad.getImage()));
        //                 ads.setPk(ad.getPk());
        //                 ads.setPrice(ad.getPrice());
        //                 ads.setTitle(ad.getTitle());
        //                 return ads;
        //     }).collect(Collectors.toList());
           
        //     adList.setResults(resultAds);
        //     adList.setCount(resultAds.size());
        //     return adList;
        // }

        // для отладки
        Ads ads = new Ads();
        Ad ad = new Ad();

        ad.setAuthor(1);
        ad.setImage("ad.getImage()");
        ad.setPrice(100);
        ad.setTitle("ad.getTitle()");
        ad.setDescription("desc");
        adRepository.save(ad);

        ads.setAuthor(1);
        ads.setImage(Arrays.asList("ad.getImage()"));
        ads.setPk(ad.getPk());
        ads.setPrice(100);
        ads.setTitle("ad.getTitle()");

        List<Ads> resultAds = new ArrayList<Ads>(Arrays.asList(ads)); 
        adList.setResults(resultAds);
        adList.setCount(resultAds.size());
        return adList;
    }

 
    public FullAd getFullAd(int id) {
       Ad ad = adRepository.getFullAd(id);
       Client client = clientRepository.findById(ad.getAuthor()).orElseThrow();
       FullAd fullAd = new FullAd();
       fullAd.setAuthorFirstName(client.getFirstName());
       fullAd.setAuthorLastName(client.getLastName());
       fullAd.setDescription(ad.getDescription());
       fullAd.setEmail(client.getEmail());
       fullAd.setImage(adRepository.getImages(client.getId()));
       fullAd.setPhone(client.getPhone());
       fullAd.setPk(ad.getPk());
       fullAd.setPrice(ad.getPrice());
       fullAd.setTitle(ad.getTitle());
       return fullAd; 
    }

     public AdList updateAds(int id, ru.skypro.homework.model.ad.Ad update) {
       Ads ads = new Ads();
       AdList adList = new AdList();
       Client client = clientRepository.getUserName(loginReq.getUsername());
       ads.setPrice(update.getPrice());
       ads.setTitle(update.getTitle());
       ads.setImage(adRepository.getImage(id));
       ads.setAuthor(client.getId());
       ads.setPk(id);
       adRepository.updateAd(id, update.getPrice(), update.getTitle(), update.getDescription());
     
       List<Ads> resultAds = new ArrayList<Ads>(Arrays.asList(ads)); 
       adList.setResults(resultAds);
       adList.setCount(resultAds.size());

       return adList; 
    }




    // из dto в entity
    public Comment commentDtoToComment(int adPk, CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setAuthor(commentDto.getAuthor());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setText(commentDto.getText());
        comment.setAdsPk(adPk);
        return comment;
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
      public CommentDto commentToCommentDto(int id) {
          Comment comment = commentRepository.findById(id).get();
          CommentDto commentDto = new CommentDto();
          commentDto.setAuthor(comment.getAuthor());
          commentDto.setCreatedAt(comment.getCreatedAt());
          commentDto.setPk(comment.getPk());
          commentDto.setText(comment.getText());
          return commentDto;
      }

    // from dto to entity
    public ImageDto imageDtoToImage(int id, MultipartFile imageFile) throws IOException {
        ImageDto imageDto = new ImageDto();
        Image image = new Image();
        try (
            InputStream inputStream = imageFile.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
            ) {
                byte[] imageByteas = bufferedInputStream.readAllBytes();
                image.setId(id);
                image.setFileName(imageFile.getOriginalFilename());
                image.setFileSize(imageFile.getSize());
                image.setMediaType(imageFile.getContentType());
                image.setFileName(imageFile.getOriginalFilename());
                image.setData(imageByteas);
                logger.info("image loaded");
            } catch(IOException exception) {
                logger.info(exception.getMessage());
            }

            imageRepository.save(image);
            List<String> getAddedImages = imageRepository.getAddedImages(); 
            logger.info(getAddedImages + " images");
            imageDto.setImage(getAddedImages);
        return imageDto;
    }

}
