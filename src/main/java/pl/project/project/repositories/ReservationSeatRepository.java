package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.project.project.models.ReservationSeat;
import pl.project.project.models.Show;

import java.util.List;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat,Integer> {
    @Query("select rs from ReservationSeat rs where rs.show = :show")
    List<ReservationSeat> findReservedSeatByScreening(@Param("show") Show show);
}
