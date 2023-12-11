package utez.edu.mx.dulceria.visit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.dulceria.visit.model.Visit;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findAll();
    boolean existsById(long id);

    Visit findById(long id);
}
