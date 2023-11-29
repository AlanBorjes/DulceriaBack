package utez.edu.mx.dulceria.statusOrder.model;

import utez.edu.mx.dulceria.order.model.Order;

import javax.persistence.*;
import java.util.List;

@Entity
public class Status_order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String desciprtion;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Order> order;

    public Status_order() {
    }
    public Status_order(String desciprtion) {
        this.desciprtion = desciprtion;
    }
    public Status_order(long id, String desciprtion) {
        this.id = id;
        this.desciprtion = desciprtion;
    }

    public Status_order(long id, String desciprtion, List<Order>  order) {
        this.id = id;
        this.desciprtion = desciprtion;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesciprtion() {
        return desciprtion;
    }

    public void setDesciprtion(String desciprtion) {
        this.desciprtion = desciprtion;
    }

}
