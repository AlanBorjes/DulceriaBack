package utez.edu.mx.dulceria.visits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.visits.model.Visits;

public interface VisitsRepository extends JpaRepository<Visits,Long> {
}
