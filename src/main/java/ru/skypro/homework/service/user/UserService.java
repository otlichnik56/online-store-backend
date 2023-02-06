package ru.skypro.homework.service.user;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.repository.ClientRepository;
import ru.skypro.homework.service.ad.AdsUser;
import ru.skypro.homework.model.user.User;
import ru.skypro.homework.service.Mapper;

@Service
@AllArgsConstructor
public class UserService implements UserInterface {

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


    @Override
    public User updateUser() {
        return new User(1, "jon", "slith", "test@mail.ru", "89991234545",
                "123", "Moscow", "image" );
    }

    @Override
    public User updateUserImage() {
        return new User(0, "imageUpdate", null, null, null, null, null, null);
    }

    @Override
    public AdsUser getAdsMe() {
        return mapper.clientToAdsUser();
    }

   
}
