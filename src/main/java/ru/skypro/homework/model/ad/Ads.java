package ru.skypro.homework.model.ad;

import java.util.List;

import lombok.Data;

@Data
public class Ads {
    private int count;
    private List<Ad> results;

}
