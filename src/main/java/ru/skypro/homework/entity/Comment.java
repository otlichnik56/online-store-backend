package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    private Integer pk;

    private String createdAt;
    private String text;

    @JoinColumn(name = "author")
    private Integer author;

}
