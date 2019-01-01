package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.project.models.Hall;


public interface HallRepository extends JpaRepository<Hall,Integer> {
}
