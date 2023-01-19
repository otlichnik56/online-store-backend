package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ad")
public class Ad  {

    @Id
    @GeneratedValue
    private Integer pk;

    private String title;
    private Integer price;
    private String image;
    private String description;

    @JoinColumn(name = "author")
    private Integer author;

}
