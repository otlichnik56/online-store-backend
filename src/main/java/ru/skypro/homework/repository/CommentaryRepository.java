package ru.skypro.homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import ru.skypro.homework.entity.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {

    @Query(value = "SELECT * FROM comments WHERE ads_pk = :adsPk", nativeQuery = true)
    List<Commentary> getAllAdsComments(@Param("adsPk") Integer adsPk);
}
