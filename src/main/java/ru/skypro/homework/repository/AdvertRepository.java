package ru.skypro.homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Advert;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Integer> {

    @Query(value = "SELECT * FROM ads WHERE author = :authorId", nativeQuery = true)
    List<Advert> getAd(@Param("authorId") Integer authorId);

    @Query(value = "SELECT * FROM ads", nativeQuery = true)
    List<Advert> getAllAds();

}
