package utez.edu.mx.dulceria.orderHasProduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.order.model.Order;
import utez.edu.mx.dulceria.orderHasProduct.model.Order_has_Product;
import utez.edu.mx.dulceria.product.model.Product;

public interface OrderHasProductRepository extends JpaRepository<Order_has_Product, Long> {
    Order_has_Product findByOrderAndProduct(Order order, Product product);
}
