package utez.edu.mx.dulceria.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.user.model.User;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByUsername (String username);

}
