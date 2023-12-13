package utez.edu.mx.dulceria.visit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.dulceria.visit.model.Visit;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findAll();

    List<Visit> findByStoreId(long id);
    List<Visit> findByStoreDeliverId(long id);
    boolean existsById(long id);

    Visit findById(long id);

    List<Visit> findByStoreDeliverIdAndStatusIdNot(long deliver, long status);
}
