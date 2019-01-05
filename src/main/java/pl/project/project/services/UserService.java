package pl.project.project.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.project.project.models.User;

public interface UserService {

    void save(User user);
    User findByUsername(String username);


    boolean isUniqueLogin(String login);
}
