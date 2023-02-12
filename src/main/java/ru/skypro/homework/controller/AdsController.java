package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ru.skypro.homework.model.ad.AdList;
import ru.skypro.homework.model.ad.Ads;
import ru.skypro.homework.model.ad.CreateAds;
import ru.skypro.homework.model.ad.FullAd;
import ru.skypro.homework.model.comment.Comment;
import ru.skypro.homework.model.comment.CommentsList;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.comment.CommentsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final Logger logger = LoggerFactory.getLogger(AdsController.class);
    private final AdsService adsService;
    private final CommentsService commentsService;


    /** НЕ ПРОВЕРЕН
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<AdList> getAds() {
        AdList adList = adsService.getAllAds();
        if(adList != null) {
            return ResponseEntity.status(200).body(adList);
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    /** ПРОВЕРЕН
     *
     * @param authentication
     * @return
     */

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/me")
    public AdList getAdsMe(Authentication authentication) {
        logger.info("Ads Username = " + authentication.getName());
        return adsService.getAdsMe(authentication.getName());
    }

    /** НЕ ПРОВЕРЕН
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public FullAd getFullAd(@PathVariable Integer id) {
        return adsService.getFullAd(id);
    }

    /** НЕ ПРОВЕРЕН
     *
     * @param ads
     * @param file
     * @return
     * @throws IOException
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Ads> setAds(@RequestPart(value = "properties") Ads ads,
                                      @RequestPart(value = "image") MultipartFile file,
                                      Authentication authentication) throws IOException {
        return ResponseEntity.status(201).body(adsService.addAds(ads, file, authentication));
    }

    /** НЕ ПРОВЕРЕН
     *
     * @param update
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@RequestBody CreateAds update,
                                         @PathVariable Integer id,
                                         Authentication authentication) {
        return ResponseEntity.status(200).body(adsService.updateAds(id, update, authentication));
    }

    /** НЕ ПРОВЕРЕН
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAds(@PathVariable Integer id,
                                       Authentication authentication) {
        adsService.removeAds(id, authentication);
        return ResponseEntity.status(204).build();
    }

    /** НЕ ПРОВЕРЕН
     * Возвращает все коментарии к объявлению
     * @param adPk
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{ad_pk}/comments")
    public CommentsList getComments(@PathVariable(value =  "ad_pk") Integer adPk) {
        return commentsService.getAllComments(adPk);
    }

    /** НЕ ПРОВЕРЕН
     * Добавляет коментарий к объявлению
     * @param adPk
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{ad_pk}/comments")
    public Comment addComment(@PathVariable(value = "ad_pk") Integer adPk,
                              @RequestBody Comment comment) {
        return commentsService.addComments(adPk, comment);
    }


    /** НЕ ПРОВЕРЕН
     * Возвращает коментарий
     * @param adPk
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{ad_pk}/comments/{id}")
    public Comment getAdComment(@PathVariable("ad_pk") Integer adPk, @PathVariable("id") Integer id) {
        return commentsService.getComment(id);
    }

    /** НЕ ПРОВЕРЕН
     * Редактирования коментария
     * @param adPk
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/{ad_pk}/comments/{id}")
    public Comment updateComment(@PathVariable("ad_pk") Integer adPk, @PathVariable("id") Integer id,
                                 @RequestBody Comment comment,
                                 Authentication authentication) {
        return commentsService.updateComment(id, comment, authentication);
    }


    /** НЕ ПРОВЕРЕН
     * Удаляет коментарий к объявлению
     * @param adPk
     * @param id
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<?> removeComments(@PathVariable("ad_pk") Integer adPk,
                                            @PathVariable Integer id,
                                            Authentication authentication) {
        commentsService.removeComment(id, authentication);
        return ResponseEntity.ok().build();
    }

}
