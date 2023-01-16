package ru.skypro.homework.model.ad;

import java.util.Set;

import lombok.Data;

@Data
public class Ads {
    private int author;
    private Set<String> image;
    private int pk;
    private int price;
    private String title;

    public Ads() {
    }

}
