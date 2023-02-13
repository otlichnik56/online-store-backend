package ru.skypro.homework.service.auth;

import ru.skypro.homework.service.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import ru.skypro.homework.entity.Client;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.Role;
import ru.skypro.homework.repository.ClientRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final ClientRepository clientRepository;
    private final Mapper mapper;

    public AuthServiceImpl(UserDetailsManager manager, PasswordEncoder encoder, ClientRepository clientRepository, Mapper mapper) {
        this.manager = manager;
        this.encoder = encoder;
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    /** Работает
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean login(String userName, String password) {
        Client client = clientRepository.findByUsername(userName);
        if (client == null) {
            return false;
        }
        String encryptedPassword = client.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        encoder.encode(encryptedPassword);
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
        /**
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        encoder.encode(userDetails.getPassword());
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);*/
    }

    /** Работает. Нужна доработка
     *
     * @param registerReq
     * @return
     */
    @Override
    public boolean register(RegisterReq registerReq) {
        Client client = clientRepository.findByUsername(registerReq.getUsername());
        if (client == null) {
            String encryptedPassword = encoder.encode(registerReq.getPassword());
            client = mapper.registerReqToClient(registerReq);
            client.setPassword("{bcrypt}" + encryptedPassword);
            clientRepository.save(client);
            return true;
        } else {
            return false;
        }



        /**
        if (manager.userExists(registerReq.getUsername())) {
            return false;
        }
        manager.createUser(
                User.withDefaultPasswordEncoder()
                        .username(registerReq.getUsername())
                        .password(registerReq.getPassword())
                        .roles(role.name())
                        .build()
        );

        return true;*/
    }
}
