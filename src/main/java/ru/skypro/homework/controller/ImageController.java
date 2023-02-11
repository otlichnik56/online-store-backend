package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ads.AdsService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final AdsService adsService;


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        byte[] picture = adsService.getImage(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(picture.length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(picture);
    }

    @PatchMapping("/{id}")
    public String setImage(@PathVariable(value = "id") Integer id,
                           @RequestBody MultipartFile file) {
        return adsService.setImage(id, file);
    }

}
