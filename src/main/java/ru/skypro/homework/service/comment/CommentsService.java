package ru.skypro.homework.service.comment;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.model.comment.CommentDto;
import ru.skypro.homework.model.comment.CommentsList;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class CommentsService implements CommentsInterface {

    private Mapper mapper;
    private final CommentRepository commentRepository;

    @Override
    public CommentsList getAllComments(int adPk) {
        return mapper.commentToCommentDtoList(adPk);
    }

    @Override
    public CommentDto setComments(int adPk) {
        return null;
    }

    @Override
    public CommentDto getComment(int id) {
        return mapper.commentToCommentDto(id);
    }

    @Override
    public void removeComment(int pk, int id) {

    }

    @Override
    public CommentDto updateComment(int pk, int id) {
        return null;
    }

    @Override
    public CommentDto setComments(int adPk, CommentDto commentDto) {
        commentRepository.save(mapper.commentDtoToComment(adPk, commentDto));
        return commentDto;
    }

    @Override
    public CommentDto getComment(int pk, int id) {
        return null;
    }

    @Override
    public CommentDto updateComment(int adPk, int id, CommentDto commentDto) {
        Comment comment = mapper.commentDtoToComment(adPk, commentDto);
        comment.setPk(id);
        commentRepository.save(comment);
        return commentDto;
    }

}
