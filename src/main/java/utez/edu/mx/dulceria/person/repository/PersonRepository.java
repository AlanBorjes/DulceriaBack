package utez.edu.mx.dulceria.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.dulceria.person.model.Person;

public interface PersonRepository  extends JpaRepository<Person,Long> {
}

