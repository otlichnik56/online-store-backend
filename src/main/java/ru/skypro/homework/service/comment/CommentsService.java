package ru.skypro.homework.service.comment;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.model.comment.Comment;
import ru.skypro.homework.model.comment.CommentsList;

public interface CommentsService {

    CommentsList getAllComments(Integer adPk);

    Comment addComments(Integer adPk, Comment comment);

    Comment getComment(Integer id);

    Comment updateComment(Integer id, Comment comment, Authentication authentication);

    void removeComment(Integer id, Authentication authentication);

}
