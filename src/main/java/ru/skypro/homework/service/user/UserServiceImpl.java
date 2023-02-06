package ru.skypro.homework.service.user;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.model.user.NewPassword;
import ru.skypro.homework.repository.ClientRepository;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private Mapper mapper;
    private final ClientRepository clientRepository;

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


    /** НЕ ПРОВЕРЕН
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

    /** НЕ ПРОВЕРЕН
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

    //@Override
    //public AdsUser getAdsMe() {
      //  return mapper.clientToAdsUser();
    //}

    @Override
    public User updateUserImage() {
        return new User(0, "imageUpdate", null, null, null, null, null, null);
    }

   
}
