package ru.skypro.homework.model.ad;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.model.Image.Image;

@Data
@NoArgsConstructor
public class Ads {
    private int author;
    private List<Image> image;
    private int pk;
    private int price;
    private String title;

}
