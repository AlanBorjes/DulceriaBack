package utez.edu.mx.dulceria.visit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utez.edu.mx.dulceria.order.model.Order;
import utez.edu.mx.dulceria.statusVisit.model.Status_visit;
import utez.edu.mx.dulceria.store.model.Store;
import utez.edu.mx.dulceria.user.model.User;

import javax.persistence.*;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String day_visit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_visit_id")
    private Status_visit status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne(mappedBy = "visit")
    private Order order;

    public Visit() {
    }

    public Visit(long id, String day_visit, Status_visit status, Store store) {
        this.id = id;
        this.day_visit = day_visit;
        this.status = status;
        this.store = store;
    }

    public Visit(String day_visit, Status_visit status, Store store) {
        this.day_visit = day_visit;
        this.status = status;
        this.store = store;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDay_visit() {
        return day_visit;
    }

    public void setDay_visit(String day_visit) {
        this.day_visit = day_visit;
    }

    public Status_visit getStatus() {
        return status;
    }

    public void setStatus(Status_visit status) {
        this.status = status;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

}
