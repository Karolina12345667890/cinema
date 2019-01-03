package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.project.project.models.Movie;

import java.util.Date;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    @Query("select distinct m from Movie m join m.shows s where s.timeShow between :showDate and :nextDay order by m.name")
    List<Movie> findByShowDate(@Param("showDate") Date showDate, @Param("nextDay") Date nextDay);

}
