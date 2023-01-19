package ru.skypro.homework.model.comment;

import lombok.Data;

@Data
public class CommentDto {
    private Integer pk;
    private String createdAt;
    private String text;
    private Integer author;
}
