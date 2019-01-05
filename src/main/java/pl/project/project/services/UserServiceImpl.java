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

//    @Override
//    //bez adnotacji @Transactional sesja jest zamykana po wywołaniu findByUsername, co uniemożliwia dociągnięcie ról, gdyż fetch=EAGER.
//    //ponadto, musi być włączone zarządzanie transakcjami @EnableTransactionManagement
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       pl.project.project.models.User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//
//        System.out.println("jestem tuuuuuuuuuuuuuuuuu"+username);
//        return createUserDetails(user);
//    }
//
//    private UserDetails createUserDetails( pl.project.project.models.User user) {
////        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
////        for (Role role : user.getRoles()){
////            grantedAuthorities.add(new SimpleGrantedAuthority(role.getType().toString()));
////        }
//
//        Set<GrantedAuthority> grantedAuthorities =
//                user.getRoles().stream().map(//mapowanie Role na GrantedAuthority
//                        r -> new SimpleGrantedAuthority(r.getType().toString())
//                ).collect(Collectors.toSet());
//
//        System.out.println("co zwracam??????????" + grantedAuthorities);
//        return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
//                true, true, true, grantedAuthorities);
//    }
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
