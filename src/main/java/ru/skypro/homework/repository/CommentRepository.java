package ru.skypro.homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import ru.skypro.homework.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * Возвращает все комментарии к объявлению по первичному ключу объявления
     * @param adsPk
     * @return
     */
    @Query(value = "SELECT * FROM comment WHERE ads_pk =:adsPk", nativeQuery = true)
    List<Comment> getComments(@Param("adsPk") int adsPk);


}
