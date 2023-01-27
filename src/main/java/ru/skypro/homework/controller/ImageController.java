package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.skypro.homework.model.Image.ImageDto;
import ru.skypro.homework.service.ads.AdsService;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final AdsService adsService;

    @PostMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
                                  produces = {MediaType.APPLICATION_JSON_VALUE})
    public ImageDto updateAdsImage(@PathVariable Integer id, @RequestParam MultipartFile multipartFile) throws IOException {
        return adsService.updateAdsImage(id, multipartFile);
    }

}
