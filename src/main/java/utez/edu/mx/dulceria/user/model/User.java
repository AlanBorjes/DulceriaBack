package utez.edu.mx.dulceria.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column()
    private String password;
    @Column( unique = true)
    private String username;

    public User() {
    }

    public User(long id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
