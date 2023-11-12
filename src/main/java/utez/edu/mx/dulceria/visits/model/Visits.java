package utez.edu.mx.dulceria.visits.model;

import utez.edu.mx.dulceria.deliveryPerson.model.DeliveryPerson;
import utez.edu.mx.dulceria.person.model.Person;
import utez.edu.mx.dulceria.store.model.Store;

import javax.persistence.*;
import java.util.List;
//import java.time.LocalDateTime;

@Entity
public class Visits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column(nullable = false)
    //private LocalDateTime visitDateTime;

    @Column(nullable = false)
    private String dateVisit;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_person_id", referencedColumnName = "id")
    private List<DeliveryPerson> deliveryPerson;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private List<Store> store;

    @Column(nullable = false)
    private String status;



    public Visits() {
    }

    public Visits(long id, String dateVisit, List<DeliveryPerson> deliveryPerson, List<Store> store, String status) {
        this.id = id;
        this.dateVisit = dateVisit;
        this.deliveryPerson = deliveryPerson;
        this.store = store;
        this.status = status;
    }

    public Visits(String dateVisit, List<DeliveryPerson> deliveryPerson, List<Store> store, String status) {
        this.dateVisit = dateVisit;
        this.deliveryPerson = deliveryPerson;
        this.store = store;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(String dateVisit) {
        this.dateVisit = dateVisit;
    }

    public List<DeliveryPerson> getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(List<DeliveryPerson> deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public List<Store> getStore() {
        return store;
    }

    public void setStore(List<Store> store) {
        this.store = store;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
