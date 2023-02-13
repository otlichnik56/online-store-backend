package ru.skypro.homework.service.comment;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.skypro.homework.UserAccessControl;
import ru.skypro.homework.entity.Commentary;
import ru.skypro.homework.model.comment.Comment;
import ru.skypro.homework.model.comment.CommentsList;
import ru.skypro.homework.repository.CommentaryRepository;
import ru.skypro.homework.service.Mapper;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private UserAccessControl accessControl;
    private Mapper mapper;
    private final CommentaryRepository commentaryRepository;

    /** ПРОВЕРЕН
     *
     * @param adPk
     * @return
     */
    @Override
    public CommentsList getAllComments(Integer adPk) {
        List<Commentary> comments = commentaryRepository.getAllAdsComments(adPk);
        return mapper.commentaryToCommentsList(comments);
    }

    /** ПРОВЕРЕН
     *
     * @param adPk
     * @param comment
     * @return
     */
    @Override
    public Comment addComments(Integer adPk, Comment comment) {
        commentaryRepository.save(mapper.commentToCommentary(adPk, comment));
        return comment;
    }

    /** ПРОВЕРЕН
     *
     * @param id
     * @return
     */
    @Override
    public Comment getComment(Integer id) {
        Commentary commentary = commentaryRepository.findById(id).orElse(null);
        assert commentary != null;
        return mapper.commentaryToComment(commentary);
    }

    /** ПРОВЕРЕН
     *
     * @param id
     * @param comment
     * @return
     */
    @Override
    public Comment updateComment(Integer id, Comment comment, Authentication authentication) {
        Commentary commentary = commentaryRepository.findById(id).orElse(null);
        assert commentary != null;
        if (accessControl.accessControl(commentary.getAuthor(), authentication)) {
            commentaryRepository.save(mapper.commentToCommentaryEdit(commentary, comment));
            return comment;
        } else {
            return null;
        }
    }

    /** ПРОВЕРЕН
     *
     * @param id
     */
    @Override
    public void removeComment(Integer id, Authentication authentication) {
        Commentary commentary = commentaryRepository.findById(id).orElse(null);
        assert commentary != null;
        if (accessControl.accessControl(commentary.getAuthor(), authentication)) {
            commentaryRepository.deleteById(id);
        }
    }

}
