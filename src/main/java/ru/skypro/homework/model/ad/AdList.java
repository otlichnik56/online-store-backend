package ru.skypro.homework.model.ad;

import java.util.List;

import lombok.Data;
import ru.skypro.homework.entity.Ad;

@Data
public class AdList {
    private int count;
    private List<Ads> results;
}
