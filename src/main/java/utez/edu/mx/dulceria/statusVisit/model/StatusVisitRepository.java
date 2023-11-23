package utez.edu.mx.dulceria.statusVisit.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusVisitRepository extends JpaRepository<Status_visit,Long> {

    Optional<Status_visit> findById(long id);

}
