package utez.edu.mx.dulceria.person.model;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;

import utez.edu.mx.dulceria.user.model.User;
import utez.edu.mx.dulceria.store.model.Store;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column()
    private String name;
    @Column()
    private String lastName;
    @Column()
    private String address;
    @Column()
    private String phone;
    @Column()
    private String email;
    @Column()
    private int edad;
    @Column()
    private String sexo;
    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private User users;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "role_id", referencedColumnName = "id")
    // private Role role;

    @OneToOne(mappedBy = "owner")
    @JsonIgnore
    private Store owner;
    
    @OneToOne(mappedBy = "deliver")
    @JsonIgnore
    private Store deliver;
    



    public Person(String name,String lastName,String address,String phone,
        String email,int edad,String sexo,Store owner,Store deliver) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.edad = edad;
        this.sexo = sexo;
        // this.role = role;
        this.owner = owner;
        this.deliver = deliver;


    }

    public Person(long id, String name, String lastName, String address, String phone, String email, int edad, String sexo) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.edad = edad;
        this.sexo = sexo;
    }

    public Person(long id, String name, String lastName, String address, String phone, String email, int edad, String sexo, User users, Store owner, Store deliver) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.edad = edad;
        this.sexo = sexo;
        this.users = users;
        this.owner = owner;
        this.deliver = deliver;
    }

    public Person() {

    }

    public Person(long id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }

    public void setSexo(String sexo) { this.sexo = sexo; }

    // public String getRol() { return role; }

    // public void setRol(Role role) { this.role = role; }

    @JsonIgnore
    public Store getOwner() { return owner; }

    public void setOwner(Store owner) { this.owner = owner; }

    @JsonIgnore

    public Store getDeliver() { return deliver; }

    public void setDeliver(Store deliver) { this.deliver = deliver; }
    


}
