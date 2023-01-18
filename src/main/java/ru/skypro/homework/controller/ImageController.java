package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {

    @PostMapping("/{id}")
    public String updateAdsImage(@PathVariable Integer id) {
        return null;
    }

}
