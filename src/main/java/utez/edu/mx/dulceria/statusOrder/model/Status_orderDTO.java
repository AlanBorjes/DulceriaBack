package utez.edu.mx.dulceria.statusOrder.model;

import utez.edu.mx.dulceria.order.model.Order;

public class Status_orderDTO {
    private long id;
    private String desciprtion;
    private Order order;

    public Status_orderDTO() {
    }

    public Status_orderDTO(long id, String desciprtion) {
        this.id = id;
        this.desciprtion = desciprtion;
    }

    public Status_orderDTO(long id, String desciprtion, Order order) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
