package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.AdsUser;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.model.comment.CommentDto;
import ru.skypro.homework.model.comment.CommentsList;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.comment.CommentsService;
import ru.skypro.homework.service.user.UserService;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final CommentsService commentsService;
    private final UserService userService;


    @GetMapping
    public ResponseEntity<AdList> getAds() {
        if(adsService.getAllAds() != null) {
            return ResponseEntity.status(200).body(adsService.getAllAds());
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/me")
    public AdList getAdsMe() {
        return adsService.getAdsMe();
    }

    // дописан
    @GetMapping("/{id}")
    public FullAd getFullAd(@PathVariable Integer id) {
        // return adsService.getFullAd(id);
        return new FullAd();
    }

    // дописан
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAds(@PathVariable Integer id) {
        adsService.removeAds(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FullAd> updateAds(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(adsService.updateAds(id));
    }

    @PostMapping(consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<Ads> setAds(@RequestPart ru.skypro.homework.model.ad.Ad ad,
    @RequestPart(name = "image") MultipartFile file) {
        return ResponseEntity.status(200).body(adsService.addAds(ad, file));
    }

    /**
     * Возвращает все коментарии к объявлению
     * @param adPk
     * @return
     */
    @GetMapping("/{ad_pk}/comments")
    public CommentsList getAllComments(@PathVariable(value =  "ad_pk") Integer adPk) {
        return commentsService.getAllComments(adPk);
    }

    /**
     * Добавляет коментарий к объявлению
     * @param adPk
     * @return
     */
    @PostMapping("/{ad_pk}/comments")
    public CommentDto setComment(@PathVariable(value = "ad_pk") Integer adPk,
                                 @RequestBody CommentDto commentDto) {
        return commentsService.setComments(adPk, commentDto);
    }

    /**
     * Возвращает коментарий к объявлению определенного автора
     * @param adPk
     * @param id
     * @return
     */
    @GetMapping("/{ad_pk}/comments/{id}")
    public CommentDto getAdComments(@PathVariable("ad_pk") Integer adPk, @PathVariable("id") Integer id) {
        return commentsService.getComment(id);
    }

    /**
     * Редактирования коментария определенного автора
     * @param adPk
     * @param id
     * @return
     */
    @PatchMapping("/{ad_pk}/comments/{id}")
    public CommentDto updateCommentUser(@PathVariable("ad_pk") Integer adPk, @PathVariable("id") Integer id,
                                        @RequestBody CommentDto commentDto) {
        return commentsService.updateComment(adPk, id, commentDto);
    }

    /**
     * Удаляет коментарий к объявлению определенного пользователя
     * @param adPk
     * @param id
     * @return
     */
    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<?> removeComments(@PathVariable("ad_pk") Integer adPk, @PathVariable Integer id) {
        commentsService.removeComment(adPk, id);
        return ResponseEntity.ok().build();
    }

}

