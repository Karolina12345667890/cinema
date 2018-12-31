package pl.project.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.project.models.Price;

public interface PriceRepository extends JpaRepository<Price,Integer> {
}
