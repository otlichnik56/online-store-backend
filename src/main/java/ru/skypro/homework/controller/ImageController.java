package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.skypro.homework.model.Image.Image;
import ru.skypro.homework.service.ads.AdsService;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final AdsService adsService;

    @PostMapping("/{id}")
    public List<Image> updateAdsImage(@PathVariable Integer id, MultipartFile multipartFile) {
        return adsService.updateAdsImage(id, multipartFile);
    }

}
