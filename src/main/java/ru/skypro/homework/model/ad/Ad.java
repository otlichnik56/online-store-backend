package ru.skypro.homework.model.ad;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Ad {
    private int price;
    private String title;
    private String description;
    private MultipartFile image;
}
