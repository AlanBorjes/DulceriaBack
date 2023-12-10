package utez.edu.mx.dulceria.order.model;

import utez.edu.mx.dulceria.orderHasProduct.model.Order_has_Product;
import utez.edu.mx.dulceria.statusOrder.model.Status_order;
import utez.edu.mx.dulceria.visit.model.Visit;

import java.util.List;

public class OrderDTO {
    private long id;
    private String description;
    private String observaciones;
    private String incidencias;
    private Status_order status;
    private Visit visit;
    private List<Order_has_Product> productList;

    public OrderDTO() {
    }

    public OrderDTO(long id, String description, String observaciones) {
        this.id = id;
        this.description = description;
        this.observaciones = observaciones;
    }

    public OrderDTO(long id, String description, String observaciones, Status_order status, Visit visit, List<Order_has_Product> productList) {
        this.id = id;
        this.description = description;
        this.observaciones = observaciones;
        this.status = status;
        this.visit = visit;
        this.productList = productList;
    }

    public OrderDTO(long id, String description, String observaciones, String incidencias, Status_order status, Visit visit, List<Order_has_Product> productList) {
        this.id = id;
        this.description = description;
        this.observaciones = observaciones;
        this.incidencias = incidencias;
        this.status = status;
        this.visit = visit;
        this.productList = productList;
    }

    public OrderDTO(String description, String observaciones, String incidencias, Status_order status, Visit visit) {
        this.description = description;
        this.observaciones = observaciones;
        this.incidencias = incidencias;
        this.status = status;
        this.visit = visit;
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
