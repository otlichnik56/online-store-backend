package ru.skypro.homework.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.skypro.homework.model.user.User;

import java.util.Collection;

public class UserAuthentication implements Authentication {


    private final User user;

    public UserAuthentication(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return user.getFirstName();
    }
}
