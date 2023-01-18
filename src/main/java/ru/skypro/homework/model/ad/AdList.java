package ru.skypro.homework.model.ad;

import java.util.List;

import lombok.Data;

@Data
public class AdList {
    private int count;
    private List<Ads> results;
}
