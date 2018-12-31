package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.project.project.models.Movie;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

}
