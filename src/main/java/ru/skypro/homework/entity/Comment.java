package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comment")
public class Comment {

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
