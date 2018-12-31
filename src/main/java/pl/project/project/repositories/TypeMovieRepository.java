package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.project.models.TypeMovie;

public interface TypeMovieRepository extends JpaRepository<TypeMovie,Integer> {
}
