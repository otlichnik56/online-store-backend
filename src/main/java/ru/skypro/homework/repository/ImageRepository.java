package ru.skypro.homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.skypro.homework.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    
    @Query(value = "SELECT file_name FROM image", nativeQuery = true)
    List<String> getAddedImages();
}
