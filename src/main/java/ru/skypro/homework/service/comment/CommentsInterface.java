package ru.skypro.homework.service.comment;

import ru.skypro.homework.model.comment.Comment;
import ru.skypro.homework.model.comment.CommentsList;

public interface CommentsInterface {

    CommentsList getAllComments(int adPk);

    Comment setComments(int adPk);

    Comment setComments(int adPk, Comment comment);

    Comment getComment(int pk, int id);

    Comment getComment(int id);

    void removeComment(int pk, int id);

    void removeComment(int id);

    Comment updateComment(int pk, int id);

    Comment updateComment(int adPk, int id, Comment comment);
}
