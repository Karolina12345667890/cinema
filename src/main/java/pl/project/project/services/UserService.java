package pl.project.project.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.project.project.models.User;

public interface UserService extends UserDetailsService {

    void save(User user);

    boolean isUniqueLogin(String login);
}
