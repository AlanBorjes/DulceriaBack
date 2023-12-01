package utez.edu.mx.dulceria.rol.model;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import utez.edu.mx.dulceria.user.model.User;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true, length = 20)
    private String acronym;
    @Column(nullable = false, unique = true, length = 50)
    private String description;
    @ManyToMany(mappedBy = "authorities")
    @JsonIgnore
    private Set<User> users;

    public Rol() {
    }

    public Rol(long id, String acronym, String description) {
        this.id = id;
        this.acronym = acronym;
        this.description = description;
    }

    public Rol(long id) {
        this.id = id;
    }

    public Rol(String description) {
        this.description = description;
    }

    public Rol(String acronym, String description) {
        this.acronym = acronym;
        this.description = description;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", acronym='" + acronym + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                '}';
    }
}
