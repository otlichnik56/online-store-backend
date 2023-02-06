package ru.skypro.homework.service.comment;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.skypro.homework.entity.Commentary;
import ru.skypro.homework.model.comment.Comment;
import ru.skypro.homework.model.comment.CommentsList;
import ru.skypro.homework.repository.CommentaryRepository;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class CommentsService implements CommentsInterface {

    private Mapper mapper;
    private final CommentaryRepository commentaryRepository;

    @Override
    public CommentsList getAllComments(int adPk) {
        return mapper.commentaryToCommentsList(adPk);
    }

    @Override
    public Comment setComments(int adPk) {
        return null;
    }

    @Override
    public Comment getComment(int id) {
        return mapper.commentaryToComment(id);
    }

    @Override
    public void removeComment(int pk, int id) {

    }

    @Override
    public void removeComment(int id) {
        commentaryRepository.deleteById(id);
    }

    @Override
    public Comment updateComment(int pk, int id) {
        return null;
    }

    @Override
    public Comment setComments(int adPk, Comment comment) {
        commentaryRepository.save(mapper.commentToCommentary(adPk, comment));
        return comment;
    }

    @Override
    public Comment getComment(int pk, int id) {
        return null;
    }

    @Override
    public Comment updateComment(int adPk, int id, Comment comment) {
        Commentary commentary = mapper.commentToCommentary(adPk, comment);
        commentary.setPk(id);
        commentaryRepository.save(commentary);
        return comment;
    }


}
