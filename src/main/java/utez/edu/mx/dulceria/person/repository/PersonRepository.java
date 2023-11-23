package utez.edu.mx.dulceria.person.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.dulceria.person.model.Person;

@Repository
public interface PersonRepository  extends JpaRepository<Person,Long> {
    boolean existsById(long id);
    Optional<Person> findByEmail(String email);
    Optional<Person> findByPhone(String phone);

}

