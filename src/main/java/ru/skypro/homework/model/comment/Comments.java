package ru.skypro.homework.model.comment;

import java.util.List;

import lombok.Data;

@Data
public class Comments {

    private int count;
    private List<Comment> results;

}
