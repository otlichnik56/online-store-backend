package ru.skypro.homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.skypro.homework.entity.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {

    @Query(value = "SELECT * FROM comments WHERE author = ?1", nativeQuery = true)
    List<Commentary> getComments(int authorId);
}
