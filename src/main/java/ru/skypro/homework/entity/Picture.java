package ru.skypro.homework.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

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
    private long fileSize;
    private String mediaType;
    private String fileName;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] data;

    // @JsonIgnore
    // @OneToOne
    // private Client client;

    public Picture() {}
}
