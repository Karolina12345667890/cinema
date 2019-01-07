package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.project.project.models.Reservation;
import pl.project.project.models.User;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    //SELECT * FROM reservatins rr JOIN users u ON u.id = rr.users_id WHERE u.username = 'karolina'

    @Query("SELECT r FROM Reservation r JOIN r.user u WHERE u.username LIKE :user ")
    List<Reservation> findReservationByUser(@Param("user")String user);
}
