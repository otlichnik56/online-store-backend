package ru.skypro.homework.service.Comments;

public interface CommentsInterface {
  
    Comments getComments(int ad_pk);

    Comment addComments(int ad_pk);

    Comment getComment(int pk, int id);
    
    void removeComment(int pk, int id);
    
    Comment updateComment(int pk, int id);
}
