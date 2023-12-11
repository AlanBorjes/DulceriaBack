package utez.edu.mx.dulceria.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.store.model.Store;

public interface StoreRepository  extends JpaRepository<Store,Long> {
    Optional<Store> findByAddress(String address);
    boolean existsById(long id);
    Optional<Store> findById(long id);
    List<Store> findByDeliverId(long id);

}
