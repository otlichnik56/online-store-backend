package ru.skypro.homework.model.ad;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ads {
    private int author;
    private String image;
    private int pk;
    private int price;
    private String title;

}
