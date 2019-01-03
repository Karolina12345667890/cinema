package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.project.models.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByType(Role.Types type);
}
