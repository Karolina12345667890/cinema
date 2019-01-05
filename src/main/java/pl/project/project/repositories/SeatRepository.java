package pl.project.project.repositories;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.project.project.models.Hall;
import pl.project.project.models.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
    @Query("select s from Seat s where s.hall = :hall order by s.row, s.seat")
    List<Seat> findSeatByRoom(@Param("hall") Hall hall);

    @Query(value = "select s from Seat s where s.hall = :hall and s.row = :row and s.seat = :seat")
    Seat findSeatByRoomAndSeat(@Param("hall") Hall hall, @Param("row") Integer row, @Param("seat") Integer number);
}
