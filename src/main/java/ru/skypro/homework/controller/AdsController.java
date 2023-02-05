package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.model.user.User;
import ru.skypro.homework.service.ad.Ad;
import ru.skypro.homework.service.ad.AdList;
import ru.skypro.homework.service.ad.Ads;
import ru.skypro.homework.service.ad.FullAd;
import ru.skypro.homework.model.comment.Comment;
import ru.skypro.homework.model.comment.CommentsList;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.comment.CommentsService;
import ru.skypro.homework.service.user.UserService;

import javax.servlet.http.HttpSession;

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
    //@PreAuthorize("isFullyAuthenticated()")
    public AdList getAdsMe(HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("user");
        return adsService.getAdsMe(user.getId());
    }

    @GetMapping("/{id}")
    public FullAd getFullAd(@PathVariable Integer id) {
        return adsService.getFullAd(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdList> updateAds(
         @RequestBody Ad update,
         @PathVariable Integer id) {
        return ResponseEntity.status(200).body(adsService.updateAds(id, update));
    }


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Ads> setAds(@RequestPart(value = "properties") Ad ad,
    @RequestPart(value = "image") MultipartFile file) throws IOException {
        return ResponseEntity.status(201).body(adsService.addAds(ad, file));
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
    public Comment setComment(@PathVariable(value = "ad_pk") Integer adPk,
                              @RequestBody Comment comment) {
        return commentsService.setComments(adPk, comment);
    }


    /**
     * Возвращает коментарий к объявлению определенного автора
     * @param adPk
     * @param id
     * @return
     */
    @GetMapping("/{ad_pk}/comments/{id}")
    public Comment getAdComments(@PathVariable("ad_pk") Integer adPk, @PathVariable("id") Integer id) {
        return commentsService.getComment(id);
    }

    /**
     * Редактирования коментария определенного автора
     * @param adPk
     * @param id
     * @return
     */
    @PatchMapping("/{ad_pk}/comments/{id}")
    public Comment updateCommentUser(@PathVariable("ad_pk") Integer adPk, @PathVariable("id") Integer id,
                                     @RequestBody Comment comment) {
        return commentsService.updateComment(adPk, id, comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAds(@PathVariable Integer id) {
        adsService.removeAds(id);
        return ResponseEntity.status(204).build();
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
