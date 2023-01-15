package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.ad.Ad;
import ru.skypro.homework.model.comment.Comment;

import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class AdController {

    @GetMapping
    public List<Ad> getAds() {
        return null;
    }

    @GetMapping("me")
    public List<Ad> getAdsMe() {
        return null;
    }

    @GetMapping("{id}")
    public Ad getFullAd(@PathVariable String id) {
        return null;
    }

    @GetMapping("{ad_pk}/comments")
    public List<Comment> getAllAdComments(@PathVariable String ad_pk) {
        return null;
    }

    @GetMapping("{ad_pk}/comments/{id}")
    public List<Comment> getAdComments(@PathVariable String ad_pk, @PathVariable Integer id) {
        return null;
    }


    @PostMapping
    public String setAds() {
        return null;
    }

    @PostMapping("{ad_pk}/comments")
    public Comment setComment(@PathVariable String ad_pk) {
        return null;
    }


    @PutMapping("{id}")
    public List<Comment> updateAds(@PathVariable String id) {
        return null;
    }

    @PutMapping("{ad_pk}/comments/{id}")
    public List<Comment> updateAdsUser(@PathVariable String ad_pk, @PathVariable Integer id) {
        return null;
    }


    @DeleteMapping("{id}")
    public ResponseEntity removeAds(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{ad_pk}/comments/{id}")
    public ResponseEntity removeComments(@PathVariable String ad_pk, @PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

}

