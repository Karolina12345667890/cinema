package pl.project.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.project.project.models.Role;
import pl.project.project.repositories.RoleRepository;
import pl.project.project.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void save(pl.project.project.models.User user) {

        Role userRole = roleRepository.findRoleByType(Role.Types.ROLE_USER);
        List roles = Arrays.asList(userRole);
        user.setRoles(new HashSet<>(roles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(null);//wyzerowanie jest potrzebne ze względu na walidację
        user.setAcount(user.getAcount());
        userRepository.saveAndFlush(user);
    }

    public pl.project.project.models.User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isUniqueLogin(String login) {
        return false;
    }


}
