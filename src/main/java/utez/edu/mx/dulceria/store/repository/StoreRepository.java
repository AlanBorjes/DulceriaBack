package utez.edu.mx.dulceria.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.store.model.Store;

public interface StoreRepository  extends JpaRepository<Store,Long> {
}