package ru.skypro.homework.service.comment;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.model.comment.CommentDto;
import ru.skypro.homework.model.comment.CommentsList;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class CommentsService implements CommentsInterface {

    private Mapper mapper;

    @Override
    public CommentsList getAllComments(int adPk) {
        return mapper.commentToCommentDtoList(new Comment());
    }

    @Override
    public Comment setComments(int adPk) {
        return mapper.commentDtoToComment(new CommentDto());
    }

    @Override
    public CommentDto getComment(int pk, int id) {
        return mapper.commentToCommentDto(new Comment());
    }
    @Override
    public void removeComment(int pk, int id) {
    
    }
    @Override
    public CommentDto updateComment(int pk, int id) {
         mapper.commentDtoToComment(new CommentDto());
         return mapper.commentToCommentDto(new Comment());
    }
}
