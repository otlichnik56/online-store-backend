package ru.skypro.homework.service.ad;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ads {
    private int author;
    private List<String> image;
    private int pk;
    private int price;
    private String title;

}
