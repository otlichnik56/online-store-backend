package ru.skypro.homework.service.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl implements UserService {

    private final Mapper mapper;
    private final ClientRepository clientRepository;
    private final PictureRepository pictureRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(Mapper mapper, ClientRepository clientRepository, PictureRepository pictureRepository) {
        this.mapper = mapper;
        this.clientRepository = clientRepository;
        this.pictureRepository = pictureRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

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


    /** ПРОВЕРЕН
     *
     * @param newPassword
     * @param username
     * @return
     */
    public boolean setPassword(NewPassword newPassword, String username) {
        Client user = clientRepository.findByUsername(username);
        String encryptedPassword = user.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        if (encoder.matches(newPassword.getCurrentPassword(), encryptedPasswordWithoutEncryptionType)) {
            user.setPassword("{bcrypt}" + encoder.encode(newPassword.getNewPassword()));
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
        Picture picture = new Picture();
        if (client == null) {
            return false;
        } else {
            try {
                byte[] bytes = file.getBytes();
                picture.setImage(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (client.getImage() == null) {
                Picture avatar = pictureRepository.save(picture);
                client.setImage("/image/" + avatar.getId());
                clientRepository.save(client);
            } else {
                Integer imageId = Integer.valueOf(client.getImage().substring(7));
                picture.setId(imageId);
                pictureRepository.save(picture);
            }
            return true;
        }
    }

}
