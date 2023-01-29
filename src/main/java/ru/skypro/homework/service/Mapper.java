package ru.skypro.homework.service;
import ru.skypro.homework.model.comment.CommentDto;
import ru.skypro.homework.model.comment.CommentsList;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import ru.skypro.homework.WebSecurityConfig;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.model.Image.ImageDto;
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
        loginReq.setUsername(userName);
        loginReq.setPassword(password);
        
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
     * @param ads - дто (модель данных)
     * @return - возвращает сущность
     */

    // из dto в entity
    public Ads addAds(ru.skypro.homework.model.ad.Ad ad, MultipartFile file) {
       
        Ad adEntity = new Ad();
        Ads ads = new Ads();
        // вот здесь мы извлекаем того пользователя который у нас есть в базе
        // и который сейчас авторизовался
        Client client = clientRepository.getUserName(loginReq.getUsername());
        // далее мы считываем из базы идентификатор этого пользователя, чтобы знать
        // кто автор объявления и записываем в поле author
        adEntity.setAuthor(client.getId());
        ads.setAuthor(client.getId());
        // далее четыре сеттера мы заполняем данными
        // которые приходят с фронта в теле post запроса
       
        adEntity.setImage(file.getOriginalFilename());
        List<String> listImage = new ArrayList<>(Arrays.asList());
        listImage.add(adEntity.getImage());

        ads.setImage(listImage);
        adEntity.setPk(adEntity.getPk());

        ads.setPk(adEntity.getPk());
        
        adEntity.setPrice(ad.getPrice());
        ads.setPrice(ad.getPrice());

        adEntity.setTitle(ad.getTitle());
        ads.setTitle(ad.getTitle());

        adEntity.setDescription(ad.getDescription());
        logger.info(ads + " ads");
        // сохраняем в базе данных
        // в базе это будет выглядить так:
        // id image pk price title author
        // 1  ""    1   100 "title" id залогиненного пользователя (например 25)
        adRepository.save(adEntity);
        return ads;
    }

    // из entity в dto
    public AdList getAdsMe() {
       
        AdList adList = new AdList();
        // для получения объявлений того или иного пользователя
        // мы получаем сначала его (пользователя) из базы данных по введенному ранее логину
        Client client = clientRepository.getUserName(loginReq.getUsername());
        // далее мы по id этого пользователя берем из базы все его объявления
        // которые он добавил
        //  @Query(value = "SELECT * FROM ad WHERE author = ?1", nativeQuery = true)
        //  List<Ad> getAd(int authorId);
        // т.е если наш клиент имеет идентификатор или порядковый номер = 25
        // то запрос выше нам выкатит все объявления поля author = 25 
        List<Ad> adUser = adRepository.getAd(client.getId());
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

       // из entity в dto
       public AdList getAllAds() {
       
        AdList adList = new AdList();
        
        List<Ad> adUser = adRepository.getAllAds();
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

 

    // из entity в dto
    public FullAd adToFullAd(Ad ad) {
        Client client = clientRepository.findById(ad.getAuthor()).get();
        FullAd fullAd = new FullAd();
        fullAd.setAuthorFirstName(client.getFirstName());
        fullAd.setAuthorLastName(client.getLastName());
        fullAd.setDescription(ad.getDescription());
        fullAd.setEmail(client.getEmail());
        // fullAd.setImage(new Ads().getImage());
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
        // ad.setImage(new Image().getImage());
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
