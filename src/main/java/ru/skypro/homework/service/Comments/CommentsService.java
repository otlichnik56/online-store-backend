package ru.skypro.homework.service.Comments;

import org.springframework.stereotype.Service;

@Service
public class CommentsService implements CommentsInterface {

    @Override
    public Comments getComments(int ad_pk) {
        return new Comments();
    }

    @Override
    public Comment addComments(int ad_pk) {
        return new Comment();
    }

    @Override
    public Comment getComment(int pk, int id) {
        return new Comment();
    }
    @Override
    public void removeComment(int pk, int id) {

    }
    @Override
    public Comment updateComment(int pk, int id) {
        return new Comment(1, "create", 1, "text");
    }
}
