package ru.skypro.homework.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "image")
@AllArgsConstructor
public class Image {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private long fileSize;
    private String mediaType;
    private String fileName;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] data;

    // @JsonIgnore
    // @OneToOne
    // private Client client;

    public Image() {}
}
