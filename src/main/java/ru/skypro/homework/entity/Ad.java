package ru.skypro.homework.entity;

import java.util.Set;

import lombok.Data;

@Data
public class Ad  {
    private int author;
    private Set<String> image;
    private int pk;
    private int price;
    private String title;
    private String description;

    public Ad() {

    }

}
