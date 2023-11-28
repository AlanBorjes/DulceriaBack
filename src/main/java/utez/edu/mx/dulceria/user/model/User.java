package utez.edu.mx.dulceria.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.rol.model.Rol;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnore
    private String password;
    @Column(unique = true)
    private String username;
    private String code;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;
    @Column()
    private int status;
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Rol> authorities;

    public User() {
    }

    public User(long id, String password, String code, Person person, Set<Rol> authorities, int status) {
        this.id = id;
        this.username = person.getEmail();
        this.password = password;
        this.code = code;
        this.person = person;
        this.authorities = authorities;
        this.status = status;
    }

    public User(long id, Person person, int status, Set<Rol> authorities) {
        this.id = id;

        this.person = person;
        this.status = status;
        this.authorities = authorities;
    }

    public User(long id, int status) {
        this.id = id;
        this.status = status;
    }

    public User(long id, Set<Rol> authorities) {
        this.id = id;
        this.authorities = authorities;
    }

    public User(long id, String password, Person person, int status, Set<Rol> authorities) {
        this.id = id;
        this.password = password;
        this.person = person;
        this.status = status;
        this.authorities = authorities;
    }

    public User(String password, String username, Person person, int status, Set<Rol> authorities) {
        this.password = password;
        this.username = username;
        this.person = person;
        this.status = status;
        this.authorities = authorities;
    }

    public User(long id, String password) {
        this.id = id;
        this.password = password;
    }

    public User(String password, String code) {
        this.password = password;
        this.code = code;
    }

    public User(String password, Person person, Set<Rol> authorities, int status) {
        this.password = password;
        this.person = person;
        this.authorities = authorities;
        this.status = status;
    }

    public User(long id) {
        this.id = id;
    }

    public User(String username, Set<Rol> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public User(String password, Person person, Set<Rol> authorities) {
        this.password = password;
        this.person = person;
        this.authorities = authorities;
    }

    public User(String password, Person person, int status, Set<Rol> authorities) {
        this.password = password;
        this.person = person;
        this.status = status;
        this.authorities = authorities;
    }

    public User(String password, String username, String code, Person person, int status, Set<Rol> authorities) {
        this.password = password;
        this.username = username;
        this.code = code;
        this.person = person;
        this.status = status;
        this.authorities = authorities;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Rol> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Rol> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", code='" + code + '\'' +
                ", person=" + person +
                ", status=" + status +
                ", authorities=" + authorities +
                '}';
    }
}