package ru.skypro.homework;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Client;
import ru.skypro.homework.model.user.Role;
import ru.skypro.homework.repository.ClientRepository;

@Service
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
