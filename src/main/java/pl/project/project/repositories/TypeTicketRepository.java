package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.project.models.TypeTicket;

public interface TypeTicketRepository extends JpaRepository<TypeTicket,Integer> {
}
