package ru.skypro.homework;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Authority;
import ru.skypro.homework.repository.AuthorityRepository;

@Service
public class UserAccessControl {

    private final AuthorityRepository authorityRepository;

    public UserAccessControl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public boolean accessControl(Integer id, Authentication authentication) {
        Authority authority = authorityRepository.findByUsername(authentication.getName());
        if (authority.getAuthority().equals("ROLE_ADMIN")) {
            return true;
        } else {
            return authority.getId().equals(id);
        }
    }

}
