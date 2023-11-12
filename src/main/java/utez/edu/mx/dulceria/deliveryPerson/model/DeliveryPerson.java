package utez.edu.mx.dulceria.deliveryPerson.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.store.model.Store;
import utez.edu.mx.dulceria.visits.model.Visits;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.List;

@Entity
public class DeliveryPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    private String route;
    private boolean available;

    @OneToOne(mappedBy = "delivery_person")
    @JsonIgnore
    private Store store;

    @ManyToMany(mappedBy = "delivery_person")
    @JsonIgnore
    private List<Visits> visits;


    public DeliveryPerson() {
    }

    public DeliveryPerson(Person person, String route, boolean available, Store store) {
        this.person = person;
        this.route = route;
        this.available = available;
        this.store = store;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}

