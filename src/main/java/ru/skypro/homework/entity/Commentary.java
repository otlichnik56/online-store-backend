package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    private String createdAt;
    private String text;

    @JoinColumn(name = "author")
    private Integer author;

    @JoinColumn(name = "ads_pk")
    private Integer adsPk;

}
