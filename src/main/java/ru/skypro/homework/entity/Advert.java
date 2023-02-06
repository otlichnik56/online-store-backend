package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ads")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    private String title;
    private Integer price;
    private String text;
    private String image;
    private String description;

    @JoinColumn(name = "author")
    private Integer author;

}
