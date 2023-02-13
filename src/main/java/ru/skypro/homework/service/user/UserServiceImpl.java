package ru.skypro.homework.service.user;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.entity.Picture;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.repository.ClientRepository;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.repository.PictureRepository;
import ru.skypro.homework.service.Mapper;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private Mapper mapper;
    private final ClientRepository clientRepository;
    private final PictureRepository pictureRepository;

    /** ПРОВЕРЕН
     *
     * @param username
     * @return
     */
    @Override
    public User getUser(String username) {
        Client client = clientRepository.getUserName(username);
        return mapper.clientToUser(client);
    }


    /** Нужна доработка
     *
     * @param newPassword
     * @param username
     * @return
     */
    public boolean setPassword(NewPassword newPassword, String username) {
        Client user = clientRepository.findByUsername(username);
        if (user.getPassword().equals(newPassword.getCurrentPassword())) {
            user.setPassword(newPassword.getNewPassword());
            clientRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    /** ПРОВЕРЕН
     *
     * @param user
     * @param authentication
     * @return
     */
    @Override
    public User updateUser(User user, Authentication authentication) {
        Client client = clientRepository.findByUsername(authentication.getName());
        Client newClient = mapper.userToClient(user, client);
        clientRepository.save(newClient);
        return user;
    }

    /** ПРОВЕРЕН
     *
     * @param file
     * @param authentication
     * @return
     */
    @Override
    public boolean updateUserImage(MultipartFile file, Authentication authentication) {
        Client client = clientRepository.findByUsername(authentication.getName());
        if (client == null) {
            return false;
        } else {
            Picture picture = new Picture();
            try {
                byte[] bytes = file.getBytes();
                picture.setImage(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Picture avatar = pictureRepository.save(picture);
            client.setImage("/image/" + avatar.getId());
            clientRepository.save(client);
            return true;
        }
    }

}
