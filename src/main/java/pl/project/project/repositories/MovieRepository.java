package pl.project.project.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT m FROM Movie m WHERE "+
            "("+
            ":phrase is null OR :phrase = '' " +
            "OR upper(m.name) LIKE upper(:phrase) " +
            ")" +
            "AND" +
            "(:directory is null OR :directory = ''" +
            "OR upper(m.director) LIKE upper(:directory)" +
            ")" +
            "AND " +
            "(:country is null OR :country = ''" +
            "OR upper(m.country) LIKE upper(:country)" +
            " )")
    List<Movie> findAllMovieUsingFilter(@Param("phrase") String p, @Param("directory") String directory, @Param("country") String country);

}
