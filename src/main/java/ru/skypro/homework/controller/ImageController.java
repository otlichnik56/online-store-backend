package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.skypro.homework.entity.Picture;
import ru.skypro.homework.repository.AdvertRepository;
import ru.skypro.homework.repository.PictureRepository;
import ru.skypro.homework.service.ads.AdsService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final AdsService adsService;
    private final PictureRepository pictureRepository;
    private final AdvertRepository advertRepository;

    @GetMapping
    public ResponseEntity<byte[]> getImage() {
        Picture picture = pictureRepository.getImage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(picture.getMediaType()));
        headers.setContentLength(picture.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(picture.getData());
    }

    // @PatchMapping("/{id}")
    // public ResponseEntity<byte[]> updateImage() {

    // }

}
