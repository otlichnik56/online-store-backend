package ru.skypro.homework.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private Integer pk;
    private String createdAt;
    private String text;
    private Integer author;

    public Comment() {
        
    }
}

