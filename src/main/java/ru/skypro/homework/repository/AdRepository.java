package ru.skypro.homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.skypro.homework.entity.Ad;

public interface AdRepository extends JpaRepository<Ad, Integer> {

    @Query(value = "SELECT * FROM ads WHERE author = ?1", nativeQuery = true)
    List<Ad> getAd(int authorId);

    @Query(value = "SELECT * FROM ads", nativeQuery = true)
    List<Ad> getAllAds();


}
