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

    @GetMapping("/{ad_pk}/comments")
    public CommentsList getAllComments(@PathVariable(value =  "ad_pk") Integer adPk) {
        return commentsService.getAllComments(1);
    }

    @GetMapping("/{ad_pk}/comments/{id}")
    public CommentDto getAdComments(@PathVariable("ad_pk") Integer adPk, @PathVariable Integer id) {
        return commentsService.getComment(adPk, id);
    }


    @PostMapping(consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<Ads> setAds(@RequestPart ru.skypro.homework.model.ad.Ad ad, 
    @RequestPart(name = "image") MultipartFile file) {
        return ResponseEntity.status(200).body(adsService.addAds(ad, file));
    }


    @PostMapping("/{ad_pk}/comments")
    public CommentDto setComment(@PathVariable(value = "ad_pk") Integer adPk) {
        return commentsService.setComments(adPk);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<FullAd> updateAds(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(adsService.updateAds(id));
    }

    @PatchMapping("/{ad_pk}/comments/{id}")
    public CommentDto updateAdsUser(@PathVariable("ad_pk") Integer adPk, @PathVariable Integer id) {
        return commentsService.updateComment(adPk, id);
    }



    // дописан
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAds(@PathVariable Integer id) {
        adsService.removeAds(id);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<?> removeComments(@PathVariable("ad_pk") Integer adPk, @PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

}

