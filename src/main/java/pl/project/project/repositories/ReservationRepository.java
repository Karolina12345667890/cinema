package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.project.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
}
