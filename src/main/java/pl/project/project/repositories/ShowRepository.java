package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.project.models.Show;

public interface ShowRepository extends JpaRepository<Show,Integer> {
}
