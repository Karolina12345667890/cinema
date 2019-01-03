package pl.project.project.repositories;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.project.models.Seat;

public interface SeatRepository extends JpaRepository<Seat,Integer> {

}
