package utez.edu.mx.dulceria.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.store.model.Store;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository  extends JpaRepository<Store,Long> {
}
