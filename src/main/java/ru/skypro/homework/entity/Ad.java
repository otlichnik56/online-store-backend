package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ad")
public class Ad  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    private String title;
    private Integer price;
    private String image;
    private String description;

    @ManyToOne
    @JoinColumn(name = "author")
    private Client author;

}
