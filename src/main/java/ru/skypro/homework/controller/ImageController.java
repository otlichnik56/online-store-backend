package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.skypro.homework.entity.Picture;
import ru.skypro.homework.repository.AdvertRepository;
import ru.skypro.homework.repository.PictureRepository;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.ads.AdsServiceImpl;

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

    private final PictureRepository pictureRepository;
    private final AdsService adsService;
    private final AdvertRepository advertRepository;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        byte[] picture = adsService.getImage(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(picture.length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(picture);
    }

}
