package utez.edu.mx.dulceria.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utez.edu.mx.dulceria.orderHasProduct.model.Order_has_Product;
import utez.edu.mx.dulceria.statusOrder.model.Status_order;
import utez.edu.mx.dulceria.visit.model.Visit;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private String observaciones;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_order_id")
    private Status_order status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "visit_id", referencedColumnName = "id")
    private Visit visit;

    @OneToMany(mappedBy = "order")
    private List<Order_has_Product> productList;

    public Order() {
    }

    public Order(long id, String description, String observaciones, Status_order status) {
        this.id = id;
        this.description = description;
        this.observaciones = observaciones;
        this.status = status;
    }

    public Order(long id, String description, String observaciones, Status_order status, List<Order_has_Product> productList) {
        this.id = id;
        this.description = description;
        this.observaciones = observaciones;
        this.status = status;
        productList = productList;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Status_order getStatus() {
        return status;
    }

    public void setStatus(Status_order status) {
        this.status = status;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public List<Order_has_Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Order_has_Product> productList) {
        this.productList = productList;
    }
}
