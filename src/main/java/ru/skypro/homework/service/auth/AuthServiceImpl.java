package ru.skypro.homework.service.auth;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import ru.skypro.homework.entity.Client;
import ru.skypro.homework.model.user.RegisterReq;
import ru.skypro.homework.model.user.Role;
import ru.skypro.homework.repository.ClientRepository;
import ru.skypro.homework.service.Mapper;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final ClientRepository clientRepository;
    private final Mapper mapper;

    public AuthServiceImpl(UserDetailsManager manager, ClientRepository clientRepository, Mapper mapper) {
        this.manager = manager;
        this.clientRepository = clientRepository;
        this.mapper = mapper;
        this.encoder = new BCryptPasswordEncoder();
    }

    /** ПРОВЕРЕН
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
    }

    /** ПРОВЕРЕН
     *
     * @param registerReq
     * @param role
     * @return
     */
    @Override
    public boolean register(RegisterReq registerReq, Role role) {
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
        Client client = clientRepository.findByUsername(registerReq.getUsername());
        mapper.registerReqToClient(registerReq, client);
        clientRepository.save(client);
        return true;
    }
}
