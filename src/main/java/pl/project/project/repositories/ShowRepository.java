package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.project.project.models.Show;

import java.util.Date;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show,Integer> {
    @Query(value = "SELECT s FROM Show s WHERE s.timeShow BETWEEN :showDate AND :nextDay")
    List<Show> findByDate(@Param("showDate") Date showDate, @Param("nextDay") Date nextDay);

    @Query(value = "SELECT * FROM shows WHERE movies_id = :movieId and time_show BETWEEN :showDate AND :nextDay order by time_show", nativeQuery = true)
    List<Show> findByDateAndTitle(@Param("showDate") Date showDate, @Param("nextDay") Date nextDay, @Param("movieId") int movieId);
}
