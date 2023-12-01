package utez.edu.mx.dulceria.statusOrder.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusOrderRepository extends JpaRepository<Status_order,Long> {

    Optional<Status_order> findById(long id);
}
