package utez.edu.mx.dulceria.person.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utez.edu.mx.dulceria.store.model.Store;
import utez.edu.mx.dulceria.user.model.User;

import javax.persistence.*;

public class PersonDTO {
    private long id;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private int edad;

    private String sexo;

    private User users;


    private Store owner;

    private Store deliver;

    public PersonDTO() {
    }

    public PersonDTO(long id, String name, String lastName, String address, String phone, String email, int edad, String sexo, User users, Store owner, Store deliver) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Store getOwner() {
        return owner;
    }

    public void setOwner(Store owner) {
        this.owner = owner;
    }

    public Store getDeliver() {
        return deliver;
    }

    public void setDeliver(Store deliver) {
        this.deliver = deliver;
    }
}
