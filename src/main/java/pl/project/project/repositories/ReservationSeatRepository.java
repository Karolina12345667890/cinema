package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.project.models.ReservationSeat;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat,Integer> {
}
