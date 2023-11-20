package utez.edu.mx.dulceria.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsById(long id);
}
