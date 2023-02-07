package ru.skypro.homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.skypro.homework.entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    
    @Query(value = "SELECT file_name FROM images", nativeQuery = true)
    List<String> getAddedImages();


    @Query(value = "SELECT * FROM images LIMIT 1", nativeQuery = true)
    Picture getImage();
}
