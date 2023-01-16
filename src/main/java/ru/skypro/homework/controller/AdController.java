package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.ad.Ad;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.model.comment.Comment;
import ru.skypro.homework.model.comment.Comments;

import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    @GetMapping
    public List<Ad> getAds() {
        return null;
    }

    @GetMapping("/me")
    public List<Ads> getAdsMe() {
        return null;
    }

    @GetMapping("/{id}")
    public FullAd getFullAd(@PathVariable Integer id) {
        return null;
    }

    @GetMapping("/{ad_pk}/comments")
    public Comments getAllAdComments(@PathVariable Integer adPk) {
        return null;
    }

    @GetMapping("/{ad_pk}/comments/{id}")
    public List<Comment> getAdComments(@PathVariable Integer adPk, @PathVariable Integer id) {
        return null;
    }


    @PostMapping
    public String setAds() {
        return null;
    }

    @PostMapping("/{ad_pk}/comments")
    public Comment setComment(@PathVariable Integer adPk) {
        return null;
    }


    @PutMapping("/{id}")
    public ResponseEntity<FullAd> updateAds(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new FullAd("descr", 10, "title"));
    }

    @PutMapping("/{ad_pk}/comments/{id}")
    public List<Comment> updateAdsUser(@PathVariable Integer adPk, @PathVariable Integer id) {
        return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity removeAds(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity removeComments(@PathVariable Integer adPk, @PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

}

