package utez.edu.mx.dulceria.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.user.model.User;

public interface UserRepository  extends JpaRepository<User,Long> {
}
