package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.project.models.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
