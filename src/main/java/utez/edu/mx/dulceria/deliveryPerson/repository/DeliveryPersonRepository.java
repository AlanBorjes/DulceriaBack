package utez.edu.mx.dulceria.deliveryPerson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.deliveryPerson.model.DeliveryPerson;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {
}
