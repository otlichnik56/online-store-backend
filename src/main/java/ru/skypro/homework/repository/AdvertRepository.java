package ru.skypro.homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ru.skypro.homework.entity.Advert;

public interface AdvertRepository extends JpaRepository<Advert, Integer> {


    @Query(value = "SELECT * FROM ads WHERE author = :authorId", nativeQuery = true)
    List<Advert> getAd(@Param("authorId") Integer authorId);

    @Query(value = "SELECT * FROM ads", nativeQuery = true)
    List<Advert> getAllAds();

    @Query(value = "SELECT * FROM ads WHERE ads.pk = ?1 LIMIT 1", nativeQuery = true)
    Advert getFullAd(int id);

    @Query(value = "SELECT image FROM ads WHERE ads.author = ?1", nativeQuery = true)
    List<String> getImages(int id);

    @Query(value = "SELECT image FROM ads", nativeQuery = true)
    List<String> getAllImages();

    @Transactional
    @Modifying
    @Query(value = "UPDATE ads SET price = ?2, title = ?3, description = ?4 WHERE ads.pk = ?1", nativeQuery = true)
    void updateAd(int id, int price, String title, String description);

    @Query(value = "SELECT image FROM ads WHERE ads.pk = ?1", nativeQuery = true)
    List<String> getImage(int id);
    

}
