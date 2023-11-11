package utez.edu.mx.dulceria.status.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.user.model.User;

import javax.persistence.*;
import java.util.List;
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, length = 20)
    private String description;
    public Status() {
    }

    public Status(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}