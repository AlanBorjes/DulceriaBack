package utez.edu.mx.dulceria.orderHasProduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.orderHasProduct.model.Order_has_Product;

public interface OrderHasProductRepository extends JpaRepository<Order_has_Product, Long> {
}
