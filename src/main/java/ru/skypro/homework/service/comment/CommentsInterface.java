package ru.skypro.homework.service.comment;

import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.model.comment.Comments;

public interface CommentsInterface {

    Comments getComments(int ad_pk);

    Comment addComments(int ad_pk);

    Comment getComment(int pk, int id);

    void removeComment(int pk, int id);

    Comment updateComment(int pk, int id);
}
