package ru.skypro.homework.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private Integer pk;
    private String createdAt;
    private String text;
    private Integer author;


    public CommentDto() {
        
    }
}

