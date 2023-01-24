package ru.skypro.homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.skypro.homework.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "SELECT * FROM comment WHERE author = ?1", nativeQuery = true)
    List<Comment> getComments(int authorId);
}
