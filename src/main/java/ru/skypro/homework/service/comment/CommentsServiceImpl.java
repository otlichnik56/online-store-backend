package ru.skypro.homework.service.comment;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.skypro.homework.UserAccessControl;
import ru.skypro.homework.entity.Commentary;
import ru.skypro.homework.model.comment.Comment;
import ru.skypro.homework.model.comment.CommentsList;
import ru.skypro.homework.repository.ClientRepository;
import ru.skypro.homework.repository.CommentaryRepository;
import ru.skypro.homework.service.Mapper;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private UserAccessControl accessControl;
    private Mapper mapper;
    private final CommentaryRepository commentaryRepository;
    private final ClientRepository clientRepository;

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
    public Comment addComments(Integer adPk, Comment comment, Authentication authentication) {
        Integer author = clientRepository.findByUsername(authentication.getName()).getId();
        Commentary commentary = mapper.commentToCommentary(adPk, comment);
        commentary.setAuthor(author);
        Commentary commentarySave = commentaryRepository.save(commentary);
        System.out.println(commentarySave);
        return mapper.commentaryToComment(commentarySave);
    }

    /** ПРОВЕРЕН
     *
     * @param id
     * @return
     */
    @Override
    public Comment getComment(Integer id) {
        Commentary commentary = commentaryRepository.findById(id).orElse(null);
        if (commentary == null) {
            return null;
        } else {
            return mapper.commentaryToComment(commentary);
        }
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
        if (commentary == null) {
            return null;
        } else {
            if (accessControl.accessControl(commentary.getAuthor(), authentication)) {
                Commentary commentarySave = mapper.commentToCommentaryEdit(commentary, comment);
                commentarySave.setPk(id);
                commentaryRepository.save(commentarySave);
                return comment;
            } else {
                return null;
            }
        }
    }

    /** ПРОВЕРЕН
     *
     * @param id
     */
    @Override
    public boolean removeComment(Integer id, Authentication authentication) {
        Commentary commentary = commentaryRepository.findById(id).orElse(null);
        assert commentary != null;
        if (accessControl.accessControl(commentary.getAuthor(), authentication)) {
            commentaryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
