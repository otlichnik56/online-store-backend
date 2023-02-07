package ru.skypro.homework;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.model.user.Role;
import ru.skypro.homework.repository.ClientRepository;

public class UserAccessControl {

    private final ClientRepository clientRepository;

    public UserAccessControl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean accessControl(Integer id, Authentication authentication) {
        Client client = clientRepository.findByUsername(authentication.getName());
        if (client.getRole().equals(Role.ADMIN)) {
            return true;
        } else {
            return client.getId().equals(id);
        }
    }

}
