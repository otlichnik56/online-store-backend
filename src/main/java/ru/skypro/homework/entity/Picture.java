package ru.skypro.homework.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
@AllArgsConstructor
public class Picture {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Lob
    private byte[] image;


    public Picture() {}
}
