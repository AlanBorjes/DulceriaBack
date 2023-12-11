package utez.edu.mx.dulceria.statusOrder.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusOrderRepository extends JpaRepository<Status_order,Long> {

    Status_order findById(long id);
}
