package utez.edu.mx.dulceria.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.product.model.Product;

public interface ProductRepository  extends JpaRepository<Product, Long> {
    boolean existsById(long id);
}
