package ru.skypro.homework.model.ad;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FullAd {
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int pk;
    private int price;
    private String title;



    public FullAd(String description, int price, String title) {
        this.description = description;
        this.price = price;
        this.title = title;
    }

}