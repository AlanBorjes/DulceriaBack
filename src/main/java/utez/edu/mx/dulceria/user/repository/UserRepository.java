package utez.edu.mx.dulceria.user.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utez.edu.mx.dulceria.rol.model.Rol;
import utez.edu.mx.dulceria.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByUsername (String username);
    boolean existsByPersonEmail(String email);
    Optional<User> findByCode(String code);
    Optional<User> findById(long id);

    List<User> findUsersByAuthorities(Rol rol);
    Optional<User> findByPerson_Email(String email);
    void removeById(long id);
    void deleteById(long id);
    boolean existsByUsername(String username);
    boolean existsById(long id);
    boolean existsByCode(String code);
}
